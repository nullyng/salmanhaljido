package com.salmanhaljido.demo.animalhospital.service;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
public class AnimalHospitalServiceImpl implements AnimalHospitalService{

    @Override
    public void getAnimalHospitalData() throws IOException {
        String dataPath = "src/resources/data/";

        URL url = new URL("https://www.localdata.go.kr/datafile/each/02_03_01_P.xlsx");

        File destination = new File(dataPath + "animalhospital.xlsx");
        FileUtils.copyURLToFile(url,destination);

        FileInputStream fileInputStream = new FileInputStream(dataPath + "animalhospital.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        FileOutputStream fileOutputStream = new FileOutputStream(dataPath + "ah.data");

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

                if("정상".equals(state)){
                    if (!"".equals(generalAddr)) fileOutputStream.write(generalAddr.getBytes(StandardCharsets.UTF_8));
                    else fileOutputStream.write(streetAddr.getBytes(StandardCharsets.UTF_8));

                    fileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
                }
            }
        }

        SparkSession session = SparkSession.builder()
                .master("local")
                .appName("ah")
                .config("spark.mongodb.write.connection.uri", "mongodb://127.0.0.1/openapi.ah")
                .getOrCreate();

        Dataset<Row> df = session.read().text(dataPath + "ah.data");
        JavaRDD<Row> rdd = df.toJavaRDD();

        JavaRDD<String> rdds = rdd.map(line -> {
            String[] tokens = line.toString().split("\\[|\\s");

            if(tokens.length < 3) return null;

            if(tokens[1].endsWith("시")){
                if(tokens[2].endsWith("동") || tokens[2].endsWith("읍") || tokens[2].endsWith("면")) return tokens[1];
                else if(tokens[2].endsWith("구") || tokens[2].endsWith("군")) return tokens[1] + " " + tokens[2];
            }else if(tokens[1].endsWith("도")){
                if (tokens[2].endsWith("군")) return tokens[1] + " " + tokens[2];
                else if(tokens[2].endsWith("시")){
                    if(tokens[3].endsWith("구") || tokens[3].endsWith("군")){
                        return tokens[1] + " " + tokens[2] + " " + tokens[3];
                    }
                    return tokens[1] + " " + tokens[2];
                }
            }
            return null;
        });

        File writeFile = new File(dataPath + "ah_result.json");
        fileOutputStream = new FileOutputStream(writeFile);

        Map<String, Long> map = rdds.countByValue();

        for(String str : map.keySet()){
            JSONObject value = new JSONObject();
            value.put(str, map.get(str));
            fileOutputStream.write(value.toString().getBytes());
            fileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
        }

        Dataset<Row> dff = session.read().format("json").load(dataPath + "ah_result.json");
        dff.write().format("mongodb").mode("overwrite").save();

    }

}
