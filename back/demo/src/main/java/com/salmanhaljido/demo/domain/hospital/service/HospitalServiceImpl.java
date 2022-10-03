package com.salmanhaljido.demo.domain.hospital.service;


import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.json.JSONObject;

import java.io.*;
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

    @Override
    public void getData() throws Exception{

        String dataPath = "src/main/resources/data/";
        URL url = new URL("https://www.localdata.go.kr/datafile/each/01_01_01_P.xlsx");

        File destination = new File(dataPath + "hospital.xlsx");
        FileUtils.copyURLToFile(url,destination);

        FileInputStream fileInputStream = new FileInputStream(dataPath + "hospital.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        FileOutputStream fileOutputStream = new FileOutputStream(dataPath + "hospital.data");

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
                .config("spark.mongodb.write.connection.uri", "mongodb://admin:salmand110@j7d110.p.ssafy.io/openapi.hospital?authSource=admin")
                .getOrCreate();

        Dataset<Row> df = session.read().text(dataPath + "hospital.data");
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



        File writeFile = new File(dataPath + "hospital_result.json");
        fileOutputStream = new FileOutputStream(writeFile);

        Map<String, Long> map = rdds.countByValue();
        for(String str : map.keySet()){
            JSONObject value = new JSONObject();
            if(str == null) continue;
            if(str.charAt(str.length()-1)==' ') str = str.substring(0, str.length()-1);

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
        Dataset<Row> dff = session.read().format("json").load(dataPath + "hospital_result.json");
        dff.write().format("mongodb").mode("overwrite").save();
        session.close();
        System.out.println("Hospital : Finish");
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

}
