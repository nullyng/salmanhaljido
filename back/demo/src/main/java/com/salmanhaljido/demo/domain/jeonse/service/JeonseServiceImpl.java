package com.salmanhaljido.demo.domain.jeonse.service;


import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


@Service
public class JeonseServiceImpl implements JeonseService {

    @Override
    public void getData() throws Exception {

        String dataPath = "src/main/resources/data/";
        File file = new File(dataPath + "jeonse.data");
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        Map<String, String> sidoMap = new HashMap<>();
        sidoMap.put("서울", "서울특별시");
        sidoMap.put("부산", "부산광역시");
        sidoMap.put("대구", "대구광역시");
        sidoMap.put("인천", "인천광역시");
        sidoMap.put("광주", "광주광역시");
        sidoMap.put("대전", "대전광역시");
        sidoMap.put("울산", "울산광역시");
        sidoMap.put("세종", "세종특별자치시");
        sidoMap.put("경기", "경기도");
        sidoMap.put("강원", "강원도");
        sidoMap.put("충북", "충청북도");
        sidoMap.put("충남", "충청남도");
        sidoMap.put("전북", "전라북도");
        sidoMap.put("전남", "전라남도");
        sidoMap.put("경북", "경상북도");
        sidoMap.put("경남", "경상남도");
        sidoMap.put("제주", "제주특별자치도");

        try {
            StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15067573/v1/uddi:d2dae93c-51eb-4873-983e-a71fdf4835f9?page=1&perPage=236&serviceKey=0bIH%2Foy8BRfa%2BdR%2BGuAe6E0gn%2BBo0k5OV6GaiweFfXeZ7q7dxRea0mhVPAtK%2BoMdsRKfXH1lfsRoYQ3hSn5v8w%3D%3D");
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
            }
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            for(int i=0;i<json.getJSONArray("data").length();i++) {
                String tokens[] = json.getJSONArray("data").getJSONObject(i).get("지역").toString().split(" ");
                String s = "";

                if(!sidoMap.containsKey(tokens[0])) continue;
                if(tokens.length ==2 && tokens[1].contains("(구)")){
                    tokens[1] = tokens[1].substring(tokens[1].indexOf("(구)")+3);
                }
                if(tokens.length ==3){
                    if(tokens[1].contains("(구)"))
                        tokens[1] = tokens[1].substring(tokens[1].indexOf("(구)")+3);
                    if(tokens[2].contains("(구)"))
                        tokens[2] = tokens[2].substring(tokens[2].indexOf("(구)")+3);
                }
                if(tokens.length==1){
                    s+=sidoMap.get(tokens[0]);
                }else if(tokens.length==2){
                    s+=sidoMap.get(tokens[0])+" " + tokens[1];
                }else{
                    s+=sidoMap.get(tokens[0]) + " " + tokens[1] + " " + tokens[2];
                }
                s = s + " " + json.getJSONArray("data").getJSONObject(i).get("2021-02").toString();
                s+="," + json.getJSONArray("data").getJSONObject(i).get("2021-03").toString();
                s+="," + json.getJSONArray("data").getJSONObject(i).get("2021-04").toString();
                s+="," + json.getJSONArray("data").getJSONObject(i).get("2021-05").toString();
                s+="," + json.getJSONArray("data").getJSONObject(i).get("2021-06").toString();
                s+="," + json.getJSONArray("data").getJSONObject(i).get("2021-07").toString();
                fileOutputStream.write(s.getBytes(StandardCharsets.UTF_8));
                fileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));


                rd.close();
                conn.disconnect();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SparkSession session = SparkSession.builder()
                .master("local")
                .appName("jeonse")
                .config("spark.mongodb.write.connection.uri", "mongodb://127.0.0.1/openapi.jeonse")
                .getOrCreate();
        Dataset<Row> df = session.read().text(dataPath + "jeonse.data");
        JavaRDD<Row> rdd = df.toJavaRDD();

        JavaRDD<String> rdds = rdd.map(line -> {

            return line.toString();

        });
        File writeFile = new File(dataPath + "jeonse_result.json");
        fileOutputStream = new FileOutputStream(writeFile);

        Map<String, Long> map = rdds.countByValue();
        for(String str : map.keySet()){
            if(str==null) continue;
            JSONObject value = new JSONObject();
            String token[] = str.substring(1).split(" ");
            if(token.length==1) continue;
            String sd = "";
            String sgg = "";
            String price="";
            if(token.length==2){
                sd = token[0];
                price = token[1];
            }else if(token.length==3){
                sd = token[0];
                sgg = token[1];
                price = token[2];
            }else{
                sd = token[0];
                sgg=token[1] + " " + token[2];
                price = token[3];
            }
            price = price.substring(0, price.length()-1);

            value.put("sd", sd);
            value.put("sgg", sgg);
            value.put("price", price);
            fileOutputStream.write(value.toString().getBytes());
            fileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
        }
        Dataset<Row> dff = session.read().format("json").load(dataPath + "jeonse_result.json");
        dff.write().format("mongodb").mode("overwrite").save();
        session.close();
        System.out.println("Jeonse : Finish");
    }
    private static String checkEMDG(String token){
        int index = token.length()-1;
        String s="";
        while(index>=0){
            if(token.charAt(index)=='동' || token.charAt(index)=='가' || token.charAt(index)=='면' || token.charAt(index)=='읍')
                break;
            index--;
        }
        while(index>=0){
            if(token.charAt(index)=='(' || token.charAt(index)==',' || token.charAt(index)==' ') break;
            s = token.charAt(index) + s;
            index--;
        }
        return s;
    }
    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
