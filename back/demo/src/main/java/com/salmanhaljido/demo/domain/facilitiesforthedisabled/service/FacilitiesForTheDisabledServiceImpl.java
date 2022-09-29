package com.salmanhaljido.demo.domain.facilitiesforthedisabled.service;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class FacilitiesForTheDisabledServiceImpl implements FacilitiesForTheDisabledService {
    static String API_KEY = "0bIH%2Foy8BRfa%2BdR%2BGuAe6E0gn%2BBo0k5OV6GaiweFfXeZ7q7dxRea0mhVPAtK%2BoMdsRKfXH1lfsRoYQ3hSn5v8w%3D%3D";
    @Override
    public void getData() throws Exception {

        String dataPath = "src/main/resources/data/";
        File file = new File(dataPath + "facilitiesforthedisabled.data");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            for(int pageNum=1;pageNum<144;pageNum++){
                if(pageNum==99) API_KEY = "cpiQE6bXa6VTTsmwRbrZ5DcSFPVwMSE89Mzlhywfd%2FarvokycGzqzhxazNsV06bS1BAfE9RcwakqRZ4nYWUPZg%3D%3D";
                StringBuilder urlBuilder = new StringBuilder("https://www.bokjiro.go.kr/ssis-tbu/getDisConvFaclList.do?serviceKey=" + API_KEY + "&pageNo=" + pageNum + "&numOfRows=1000&SG_APIM=2ug8Dm9qNBfD32JLZGPN64f3EoTlkpD8kSOHWfXpyrY");
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
                JSONObject json = XML.toJSONObject(jsonText);
                for(int i=0;i<json.getJSONObject("facInfoList").getJSONArray("servList").length();i++){
                    String s = json.getJSONObject("facInfoList").getJSONArray("servList").get(i).toString();
                    String tokens[] = s.substring(s.indexOf("lcMnad")).split("\"");
                    fileOutputStream.write(tokens[2].getBytes(StandardCharsets.UTF_8));
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
                .appName("facilitiesforthedisabled")
                .config("spark.mongodb.write.connection.uri", "mongodb://127.0.0.1/openapi.facilitiesforthedisabled")
                .getOrCreate();
        Dataset<Row> df = session.read().text(dataPath + "facilitiesforthedisabled.data");
        JavaRDD<Row> rdd = df.toJavaRDD();

        JavaRDD<String> rdds = rdd.map(line -> {
            String[] tokens = line.toString().split(" ");
            if(tokens.length < 3) return null;

            if(tokens[0].endsWith("시")){
                if(tokens[1].endsWith("동") || tokens[1].endsWith("읍") || tokens[1].endsWith("면")) {
                    return tokens[0];
                }
                else if(tokens[1].endsWith("구") || tokens[1].endsWith("군")){
                    String s= checkEMDG(tokens[2]);
                    return tokens[0] + " " + tokens[1] + " " + s;
                }
            }else if(tokens[0].endsWith("도")){
                if (tokens[1].endsWith("군")) {
                    String s= checkEMDG(tokens[2]);
                    return tokens[0] + " " + tokens[1] + " " + s;
                }
                else if(tokens[1].endsWith("시")){
                    if(tokens[2].endsWith("구") || tokens[2].endsWith("군")){
                        String s= checkEMDG(tokens[3]);
                        return  tokens[0] + " " + tokens[1] + " " + tokens[2] + " " + s;

                    }
                    return  tokens[0] + " " + tokens[1];
                }
            }
            return null;
        });
        File writeFile = new File(dataPath + "facilitiesforthedisabled_result.json");
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
        Dataset<Row> dff = session.read().format("json").load(dataPath + "facilitiesforthedisabled_result.json");
        dff.write().format("mongodb").mode("overwrite").save();
        session.close();
        System.out.println("FacilitiesForTheDisabled : Finish");
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
