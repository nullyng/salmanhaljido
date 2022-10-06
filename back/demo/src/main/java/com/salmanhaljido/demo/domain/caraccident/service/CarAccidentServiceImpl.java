package com.salmanhaljido.demo.domain.caraccident.service;


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
import java.util.*;

@Service
public class CarAccidentServiceImpl implements CarAccidentService {

    static int[] sidoArray = {11, 26, 27, 28, 29, 30, 31, 36, 41, 42, 43, 44, 45, 46, 47, 48, 50};

    @Override
    public void getData() throws Exception{
        String dataPath = "src/main/resources/data/";
        File file = new File(dataPath+ "caraccident.data");
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        try{
            for(int sidoCnt=0;sidoCnt<sidoArray.length;sidoCnt++){
                StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552061/frequentzoneLg/getRestFrequentzoneLg?serviceKey=0bIH%2Foy8BRfa%2BdR%2BGuAe6E0gn%2BBo0k5OV6GaiweFfXeZ7q7dxRea0mhVPAtK%2BoMdsRKfXH1lfsRoYQ3hSn5v8w%3D%3D&searchYearCd=2021&siDo=" + sidoArray[sidoCnt] + "&guGun=&type=json&numOfRows=100&pageNo=1");
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

                for(int i=0;i<json.getJSONObject("items").getJSONArray("item").length();i++){
                    fileOutputStream.write(json.getJSONObject("items").getJSONArray("item").getJSONObject(i).get("spot_nm").toString().getBytes(StandardCharsets.UTF_8));
                    fileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
                }
                rd.close();
                conn.disconnect();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        SparkSession session = SparkSession.builder()
                .master("local")
                .appName("caraccident")
                .config("spark.mongodb.write.connection.uri", "mongodb://admin:salmand110@j7d110.p.ssafy.io/openapi.caraccident?authSource=admin")
                .getOrCreate();

        Dataset<Row> df = session.read().text(dataPath + "caraccident.data");
        JavaRDD<Row> rdd = df.toJavaRDD();

        JavaRDD<String> rdds = rdd.map(line -> {
            String[] tokens = line.toString().split("\\(");

            return tokens[0].substring(1);
        });
        File writeFile = new File(dataPath + "caraccident_result.json");
        fileOutputStream = new FileOutputStream(writeFile);

        Map<String, Long> map = rdds.countByValue();
        for(String str : map.keySet()){
            JSONObject value = new JSONObject();
            if(str == null) continue;

            String token[] = str.split(" ");
            if(token.length==1) continue;
            String sd = "";
            String sgg = "";
            if(token.length==2){
                sd = token[0];
                sgg = token[1];

            }else if(token.length==3){
                if(token[2].endsWith("구")){
                    sd = token[0];
                    sgg=token[1] + " " + token[2];
                }else{
                    sd = token[0];
                    sgg = token[1];
                }
            }else{
                sd = token[0];
                sgg=token[1] + " " + token[2];
            }
            value.put("sd", sd);
            value.put("sgg", sgg);
            if(map.get(str) ==null) value.put("count", 0);
            else value.put("count", map.get(str));
            fileOutputStream.write(value.toString().getBytes());
            fileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));

        }
        Dataset<Row> dff = session.read().format("json").load(dataPath + "caraccident_result.json");
        dff.write().format("mongodb").mode("overwrite").save();
        session.close();
        System.out.println("CarAccident : Finish");
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
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
