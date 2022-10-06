package com.salmanhaljido.demo.domain.park.service;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
public class ParkServiceImpl implements ParkService {
    @Override
    public void getData() throws Exception {

        String dataPath = "src/main/resources/data/";
        File file = new File(dataPath + "park.data");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            for(int pageNum=1;pageNum<20;pageNum++){
                StringBuilder urlBuilder = new StringBuilder("http://api.data.go.kr/openapi/tn_pubr_public_cty_park_info_api?serviceKey=0bIH%2Foy8BRfa%2BdR%2BGuAe6E0gn%2BBo0k5OV6GaiweFfXeZ7q7dxRea0mhVPAtK%2BoMdsRKfXH1lfsRoYQ3hSn5v8w%3D%3D&pageNo=" + pageNum + "&numOfRows=1000&type=json");
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
                System.out.println(json.getJSONObject("response").getJSONObject("body").getJSONArray("items").getJSONObject(0).get("lnmadr").toString());
                for(int i=0;i<json.getJSONObject("response").getJSONObject("body").getJSONArray("items").length();i++){
                    if("".equals(json.getJSONObject("response").getJSONObject("body").getJSONArray("items").getJSONObject(i).get("lnmadr").toString())){
                        String streetAddr = json.getJSONObject("response").getJSONObject("body").getJSONArray("items").getJSONObject(i).get("rdnmadr").toString();
                        int index = streetAddr.length()-1;
                        String s="";
                        while(index>=0){
                            if(streetAddr.charAt(index)=='동' && (streetAddr.charAt(index+1)==',' || streetAddr.charAt(index+1)=='(' || streetAddr.charAt(index+1)==' '))
                                break;
                            index--;
                        }
                        while(index>=0){
                            if(streetAddr.charAt(index)=='(' || streetAddr.charAt(index)==',' || streetAddr.charAt(index)==' ') break;
                            s = streetAddr.charAt(index) + s;
                            index--;
                        }
                        streetAddr = streetAddr + "%" + s;
                        fileOutputStream.write(streetAddr.getBytes(StandardCharsets.UTF_8));

                    }else{
                        fileOutputStream.write(json.getJSONObject("response").getJSONObject("body").getJSONArray("items").getJSONObject(i).get("lnmadr").toString().getBytes(StandardCharsets.UTF_8));
                    }
                    fileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
                }

                rd.close();
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SparkSession session = SparkSession.builder()
                .master("local")
                .appName("park")
                .config("spark.mongodb.write.connection.uri", "mongodb://admin:salmand110@j7d110.p.ssafy.io/openapi.park?authSource=admin")
                .getOrCreate();
        Dataset<Row> df = session.read().text(dataPath + "park.data");
        JavaRDD<Row> rdd = df.toJavaRDD();

        JavaRDD<String> rdds = rdd.map(line -> {
            String[] tokens = line.toString().split(" ");
            if(tokens.length < 3) return null;

            if(tokens[0].endsWith("시")){
                if(tokens[1].endsWith("동") || tokens[1].endsWith("읍") || tokens[1].endsWith("면")) {
                    return tokens[0];
                }
                else if(tokens[1].endsWith("구") || tokens[1].endsWith("군")){
                    if(line.toString().contains("%")){
                        return tokens[0] + " " + tokens[1] + " " + line.toString().substring(line.toString().indexOf("%")+1, line.toString().length()-1);
                    }else{
                        String s= checkEMDG(tokens[2]);
                        return tokens[0] + " " + tokens[1] + " " + s;
                    }
                }
            }else if(tokens[0].endsWith("도")){
                if (tokens[1].endsWith("군")) {
                    if(line.toString().contains("%")){
                        return  tokens[0] + " " + tokens[1] + " " + line.toString().substring(line.toString().indexOf("%")+1, line.toString().length()-1);
                    }else{
                        String s= checkEMDG(tokens[2]);
                        return tokens[0] + " " + tokens[1] + " " + s;
                    }
                }
                else if(tokens[1].endsWith("시")){
                    if(tokens[2].endsWith("구") || tokens[2].endsWith("군")){
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
        File writeFile = new File(dataPath + "park_result.json");
        fileOutputStream = new FileOutputStream(writeFile);

        Map<String, Long> map = rdds.countByValue();
        for(String str : map.keySet()){
            System.out.println(str);
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
        Dataset<Row> dff = session.read().format("json").load(dataPath + "park_result.json");
        dff.write().format("mongodb").mode("overwrite").save();
        session.close();
        System.out.println("Park : Finish");
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
