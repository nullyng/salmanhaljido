package com.salmanhaljido.demo.domain.femalesafety.service;


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
import java.util.Map;


@Service
public class FemaleSafetyServiceImpl implements FemaleSafetyService {

    @Override
    public void getData() throws Exception {

        String dataPath = "src/main/resources/data/";
        File file = new File(dataPath + "femalesafety.data");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            for(int pageNum=1;pageNum<4;pageNum++){
                StringBuilder urlBuilder = new StringBuilder("http://api.data.go.kr/openapi/tn_pubr_public_female_safety_prtchouse_api?serviceKey=0bIH%2Foy8BRfa%2BdR%2BGuAe6E0gn%2BBo0k5OV6GaiweFfXeZ7q7dxRea0mhVPAtK%2BoMdsRKfXH1lfsRoYQ3hSn5v8w%3D%3D&pageNo=" + pageNum + "&numOfRows=1000&type=json");
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
                for(int i=0;i<json.getJSONObject("response").getJSONObject("body").getJSONArray("items").length();i++) {
                    if ("".equals(json.getJSONObject("response").getJSONObject("body").getJSONArray("items").getJSONObject(i).get("lnmadr").toString())) {
                        String streetAddr = json.getJSONObject("response").getJSONObject("body").getJSONArray("items").getJSONObject(i).get("rdnmadr").toString();
                        int index = streetAddr.length() - 1;
                        String s = "";
                        while (index >= 0) {
                            if (streetAddr.charAt(index) == '???' && (streetAddr.charAt(index + 1) == ',' || streetAddr.charAt(index + 1) == '(' || streetAddr.charAt(index + 1) == ' '))
                                break;
                            index--;
                        }
                        while (index >= 0) {
                            if (streetAddr.charAt(index) == '(' || streetAddr.charAt(index) == ',' || streetAddr.charAt(index) == ' ')
                                break;
                            s = streetAddr.charAt(index) + s;
                            index--;
                        }
                        streetAddr = streetAddr + "%" + s;
                        fileOutputStream.write(streetAddr.getBytes(StandardCharsets.UTF_8));

                    } else {
                        fileOutputStream.write(json.getJSONObject("response").getJSONObject("body").getJSONArray("items").getJSONObject(i).get("lnmadr").toString().getBytes(StandardCharsets.UTF_8));
                    }
                    fileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));


                    rd.close();
                    conn.disconnect();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SparkSession session = SparkSession.builder()
                .master("local")
                .appName("femalesafety")
                .config("spark.mongodb.write.connection.uri", "mongodb://admin:salmand110@j7d110.p.ssafy.io/openapi.femalesafety?authSource=admin")
                .getOrCreate();
        Dataset<Row> df = session.read().text(dataPath + "femalesafety.data");
        JavaRDD<Row> rdd = df.toJavaRDD();

        JavaRDD<String> rdds = rdd.map(line -> {
            String[] tokens = line.toString().split(" ");
            if(tokens.length < 3) return null;

            if(tokens[0].endsWith("???")){
                if(tokens[1].endsWith("???") || tokens[1].endsWith("???") || tokens[1].endsWith("???")) {
                    return tokens[0];
                }
                else if(tokens[1].endsWith("???") || tokens[1].endsWith("???")){
                    if(line.toString().contains("%")){
                        return tokens[0] + " " + tokens[1] + " " + line.toString().substring(line.toString().indexOf("%")+1, line.toString().length()-1);
                    }else{
                        String s= checkEMDG(tokens[2]);
                        return tokens[0] + " " + tokens[1] + " " + s;
                    }
                }
            }else if(tokens[0].endsWith("???")){
                if (tokens[1].endsWith("???")) {
                    if(line.toString().contains("%")){
                        return  tokens[0] + " " + tokens[1] + " " + line.toString().substring(line.toString().indexOf("%")+1, line.toString().length()-1);
                    }else{
                        String s= checkEMDG(tokens[2]);
                        return tokens[0] + " " + tokens[1] + " " + s;
                    }
                }
                else if(tokens[1].endsWith("???")){
                    if(tokens[2].endsWith("???") || tokens[2].endsWith("???")){
                        if(line.toString().contains("%")){
                            return  tokens[0] + " " + tokens[1] + " " + tokens[2] + " " + line.toString().substring(line.toString().indexOf("%")+1, line.toString().length()-1);
                        }else{
                            String s= checkEMDG(tokens[3]);
                            return  tokens[0] + " " + tokens[1] + " " + tokens[2] + " " + s;
                        }
                    }
                    return  tokens[0] + " " + tokens[1];
                }
            }
            return null;
        });
        File writeFile = new File(dataPath + "femalesafety_result.json");
        fileOutputStream = new FileOutputStream(writeFile);

        Map<String, Long> map = rdds.countByValue();
        for(String str : map.keySet()){
            if(str==null) continue;
            JSONObject value = new JSONObject();
            String token[] = str.substring(1).split(" ");
            if(token.length==1) continue;
            String sd = "";
            String sgg = "";
            if(token.length==2){
                sd = token[0];
                sgg = token[1];


            }else if(token.length==3){
                if(token[2].endsWith("???")){
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
        Dataset<Row> dff = session.read().format("json").load(dataPath + "femalesafety_result.json");
        dff.write().format("mongodb").mode("overwrite").save();
        session.close();
        System.out.println("FemaleSafety : Finish");
    }
    private static String checkEMDG(String token){
        int index = token.length()-1;
        String s="";
        while(index>=0){
            if(token.charAt(index)=='???' || token.charAt(index)=='???' || token.charAt(index)=='???' || token.charAt(index)=='???')
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
