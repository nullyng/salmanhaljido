package com.salmanhaljido.demo.domain.crime.service;


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
import java.util.Iterator;
import java.util.Map;

@Service
public class CrimeServiceImpl implements CrimeService {


    @Override
    public void getCrime() throws IOException{
        String dataPath = "src/resources/data/";
        File file = new File(dataPath+ "c.data");
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
        try{
            StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/3074462/v1/uddi:f046e6e5-58f2-4716-8b74-be62f1a6c6fc_201910221520?page=1&perPage=40&serviceKey=0bIH%2Foy8BRfa%2BdR%2BGuAe6E0gn%2BBo0k5OV6GaiweFfXeZ7q7dxRea0mhVPAtK%2BoMdsRKfXH1lfsRoYQ3hSn5v8w%3D%3D");
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
            }
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);

            Map<String, Integer> map = new HashMap<>();
            for(int i=0;i<json.getJSONArray("data").length();i++){
                String arrayTokens[] = json.getJSONArray("data").getJSONObject(i).toString().substring(1, json.getJSONArray("data").getJSONObject(i).toString().length()-1).split(",");
                for(String s : arrayTokens){
                    String objectTokens[] = s.split("\"");

                    String token[] = objectTokens[1].split(" ");
                    String sido="";
                    if(token.length==1){
                        if(!sidoMap.containsKey(token[0])) continue;
                        sido = sidoMap.get(token[0]);
                    }else{
                        sido = sidoMap.get(token[0]) + " " + token[1] + "시";
                    }
                    map.put(sido, map.getOrDefault(sido, 0)+Integer.parseInt(objectTokens[3]));
                }
            }

            Iterator<String> iter = map.keySet().iterator();
            while(iter.hasNext()){
                String s = iter.next();
                fileOutputStream.write((s + " " + map.get(s).toString()).getBytes(StandardCharsets.UTF_8));
                fileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
            }

            rd.close();
            conn.disconnect();

        }catch(Exception e){
            e.printStackTrace();
        }

        SparkSession session = SparkSession.builder()
                .master("local")
                .appName("crime")
                .config("spark.mongodb.write.connection.uri", "mongodb://127.0.0.1/openapi.c")
                .getOrCreate();

        Dataset<Row> df = session.read().text(dataPath + "c.data");
        JavaRDD<Row> rdd = df.toJavaRDD();

        JavaRDD<String> rdds = rdd.map(line -> {
            return line.toString();
        });
        File writeFile = new File(dataPath + "c_result.json");
        fileOutputStream = new FileOutputStream(writeFile);

        Map<String, Long> map = rdds.countByValue();
        for(String str : map.keySet()){
            JSONObject value = new JSONObject();
            if(str == null) continue;

            String token[] = str.split(" ");
            String sd = "";
            String sgg = "";
            String count = "0";
            if(token.length==2){
                sd = token[0];
                count=token[1];
            }else{
                sd = token[0];
                sgg=token[1];
                count=token[2];
            }
            value.put("sd", sd.substring(1));
            value.put("sgg", sgg);

            value.put("count", count.substring(0, count.length()-1));
            fileOutputStream.write(value.toString().getBytes());
            fileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));

        }
        Dataset<Row> dff = session.read().format("json").load(dataPath + "c_result.json");
        dff.write().format("mongodb").mode("overwrite").save();

        System.out.println("mongodb : finish");
    }
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
