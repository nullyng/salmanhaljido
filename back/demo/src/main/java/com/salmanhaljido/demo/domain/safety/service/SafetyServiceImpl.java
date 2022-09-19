package com.salmanhaljido.demo.domain.safety.service;


import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import org.json.JSONObject;
import org.springframework.stereotype.Service;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class SafetyServiceImpl implements SafetyService {

    static int[] sidoArray = {11, 26, 27, 28, 29, 30, 31, 36, 41, 42, 43, 44, 45, 46, 47, 48, 50};

    @Override
    public void getCarAccident() {

        String filePath = "CarAccident.CSV";
        File file=null;
        BufferedWriter bw = null;
        String NEWLINE = System.lineSeparator();

        try{
            file=new File(filePath);
            bw=new BufferedWriter(new FileWriter(file));

        }catch(Exception e){
            e.printStackTrace();
        }

        try{
            for(int sidoCnt=0;sidoCnt<sidoArray.length;sidoCnt++){
                StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552061/frequentzoneLg/getRestFrequentzoneLg?serviceKey=0bIH%2Foy8BRfa%2BdR%2BGuAe6E0gn%2BBo0k5OV6GaiweFfXeZ7q7dxRea0mhVPAtK%2BoMdsRKfXH1lfsRoYQ3hSn5v8w%3D%3D&searchYearCd=2021&siDo=" + sidoArray[sidoCnt] + "&guGun=&type=json&numOfRows=100&pageNo=1");
                URL url = new URL(urlBuilder.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "application/json");
                System.out.println("Response code: " + conn.getResponseCode());
                BufferedReader rd;
                if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                } else {
                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
                }
                String jsonText = readAll(rd);
                JSONObject json = new JSONObject(jsonText);

                Map<String, Integer> map = new HashMap<>();
                for(int i=0;i<json.getJSONObject("items").getJSONArray("item").length();i++){
                    String bjd_cd = json.getJSONObject("items").getJSONArray("item").getJSONObject(i).get("bjd_cd").toString().substring(2, 5);
                    bw.write(sidoArray[sidoCnt]+","+bjd_cd);
                    bw.write(NEWLINE);
                }
                rd.close();
                conn.disconnect();
            }
            bw.flush();
            bw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        JavaSparkContext sc = new JavaSparkContext("local", "First Spark App");
        JavaRDD<String> fileData = sc.textFile("CarAccident.CSV");
        JavaRDD<String[]> data = fileData.map(b -> b.split(","));

        Map<String, Integer> gugunCounts =
                data.mapToPair(s -> new scala.Tuple2<>(s[1], 1))
                        .reduceByKey((l1, l2) -> l1 + l2).collectAsMap();

        Iterator<String> iter = gugunCounts.keySet().iterator();

        while(iter.hasNext()) {
            String key = iter.next();
            int value = gugunCounts.get(key);

            System.out.println(key + " : " + value);
        }
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
