package com.salmanhaljido.demo.domain.hospital.service;


import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.net.URL;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@Service
public class HospitalServiceImpl implements HospitalService {

    static String API_KEY = "cpiQE6bXa6VTTsmwRbrZ5DcSFPVwMSE89Mzlhywfd%2FarvokycGzqzhxazNsV06bS1BAfE9RcwakqRZ4nYWUPZg%3D%3D";
    @Override
    public void getHospital() throws IOException{

        String dataPath = "src/resources/data/";
        URL url = new URL("https://www.localdata.go.kr/datafile/each/01_01_01_P.xlsx");

        File destination = new File(dataPath + "hospital.xlsx");
        FileUtils.copyURLToFile(url,destination);

        FileInputStream fileInputStream = new FileInputStream(dataPath + "hospital.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        FileOutputStream fileOutputStream = new FileOutputStream(dataPath + "h.data");

        int rowindex=0;

        XSSFSheet sheet=workbook.getSheetAt(0);

        int rows=sheet.getPhysicalNumberOfRows();

        for(rowindex=0;rowindex<rows;rowindex++){
            XSSFRow row=sheet.getRow(rowindex);

            if(row !=null){
                XSSFCell cell=row.getCell(10);
                String state = cell.getStringCellValue()+"";

                cell = row.getCell(18);
                String generalAddr = cell.getStringCellValue()+"";

                cell = row.getCell(19);
                String streetAddr = cell.getStringCellValue()+"";

                if("정상".equals(state) || "영업중".equals(state)){
                    if (!"".equals(generalAddr)) {
                        fileOutputStream.write(generalAddr.getBytes(StandardCharsets.UTF_8));
                    }
                    else {
                        if(streetAddr.contains("(")){
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
                        }
                        fileOutputStream.write(streetAddr.getBytes(StandardCharsets.UTF_8));
                    }
                    fileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
                }
            }
        }
        SparkSession session = SparkSession.builder()
                .master("local")
                .appName("hospital")
                .config("spark.mongodb.write.connection.uri", "mongodb://127.0.0.1/openapi.h")
                .getOrCreate();

        Dataset<Row> df = session.read().text(dataPath + "h.data");
        JavaRDD<Row> rdd = df.toJavaRDD();

        JavaRDD<String> rdds = rdd.map(line -> {
            String[] tokens = line.toString().split("\\[|\\s");
            if(tokens.length < 3) return null;

            if(tokens[1].endsWith("시")){
                if(tokens[2].endsWith("동") || tokens[2].endsWith("읍") || tokens[2].endsWith("면")) {
                    return tokens[1];
                }
                else if(tokens[2].endsWith("구") || tokens[2].endsWith("군")){
                    if(line.toString().contains("%")){
                        return tokens[1] + " " + tokens[2] + " " + line.toString().substring(line.toString().indexOf("%")+1, line.toString().length()-1);
                    }else{
                        String s= checkEMDG(tokens[3]);
                        return tokens[1] + " " + tokens[2] + " " + s;
                    }
                }
            }else if(tokens[1].endsWith("도")){
                if (tokens[2].endsWith("군")) {
                    if(line.toString().contains("%")){
                        return  tokens[1] + " " + tokens[2] + " " + line.toString().substring(line.toString().indexOf("%")+1, line.toString().length()-1);
                    }else{
                        String s= checkEMDG(tokens[3]);
                        return tokens[1] + " " + tokens[2] + " " + s;
                    }
                }
                else if(tokens[2].endsWith("시")){
                    if(tokens[3].endsWith("구") || tokens[3].endsWith("군")){
                        if(line.toString().contains("%")){
                            return  tokens[1] + " " + tokens[2] + " " + tokens[3] + " " + line.toString().substring(line.toString().indexOf("%")+1, line.toString().length()-1);
                        }else{
                            String s= checkEMDG(tokens[4]);
                            return  tokens[1] + " " + tokens[2] + " " + tokens[3] + " " + s;
                        }
                    }
                    return  tokens[1] + " " + tokens[2];
                }
            }
            return null;
        });



        File writeFile = new File(dataPath + "h_result.json");
        fileOutputStream = new FileOutputStream(writeFile);

        Map<String, Long> map = rdds.countByValue();
        int cnt=0;
        for(String str : map.keySet()){
            JSONObject value = new JSONObject();
            System.out.println(cnt);
            cnt++;
            if(str == null) continue;
            if(str.charAt(str.length()-1)==' ') str = str.substring(0, str.length()-1);

            String token[] = str.split(" ");
            if(token.length==1) continue;

            JSONObject json = callBjdCode(str);
            try{
                if(!valuePut(json, value, fileOutputStream, map.get(str))) throw new Exception();
            }catch(Exception e){
                String match = "[0-9]";
                str = str.replaceAll(match, "");
                json = callBjdCode(str);
                try{
                    if(!valuePut(json, value, fileOutputStream, map.get(str))) throw new Exception();
                }catch(Exception e2){
                    if(token.length==2) continue;

                    String s1 = "";
                    for(int i=0;i<token.length-1;i++){
                        s1+=token[i]+" ";
                    }
                    s1 = s1.substring(0, s1.length()-1);
                    json = callBjdCode(s1);
                    try{
                        if(!valuePut(json, value, fileOutputStream, map.get(s1))) throw new Exception();
                    }catch(Exception e3){
                    }
                }
            }
        }
        Dataset<Row> dff = session.read().format("json").load(dataPath + "h_result.json");
        dff.write().format("mongodb").mode("overwrite").save();

        System.out.println("finish");
    }
    private static boolean valuePut(JSONObject json, JSONObject value, FileOutputStream fileOutputStream, Long mapValue) {
        boolean check = true;
        try{
            String sidoCd = json.getJSONArray("StanReginCd").getJSONObject(1).getJSONArray("row").getJSONObject(0).get("sido_cd").toString();
            String sggCd = json.getJSONArray("StanReginCd").getJSONObject(1).getJSONArray("row").getJSONObject(0).get("sgg_cd").toString();
            String umdCd = json.getJSONArray("StanReginCd").getJSONObject(1).getJSONArray("row").getJSONObject(0).get("umd_cd").toString();
            if(sidoCd == null || sggCd == null || umdCd == null || mapValue == null) return false;
            value.put("sido_cd", sidoCd);
            value.put("sgg_cd", sggCd);
            value.put("umd_cd", umdCd);
            value.put("count", mapValue);
            fileOutputStream.write(value.toString().getBytes());
            fileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
        }catch(Exception e){
            check=false;
        }
        return check;
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
    private static JSONObject callBjdCode(String key) {
        try{
            HttpURLConnection conn;
            do {
                StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1741000/StanReginCd/getStanReginCdList?serviceKey=" + API_KEY + "&pageNo=1&numOfRows=100&type=json&locatadd_nm=" + (URLEncoder.encode(key, "utf-8")).toString());
                URL bjdUrl = new URL(urlBuilder.toString());
                conn = (HttpURLConnection) bjdUrl.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "application/json");
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                String jsonText = readAll(rd);
                JSONObject json = new JSONObject(jsonText);
                rd.close();
                conn.disconnect();
                return json;
            }while(conn.getResponseCode() != 200);
        }catch(Exception e){

        }
        return null;
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
