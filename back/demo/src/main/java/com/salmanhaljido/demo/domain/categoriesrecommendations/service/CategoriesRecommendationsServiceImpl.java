package com.salmanhaljido.demo.domain.categoriesrecommendations.service;


import com.salmanhaljido.demo.domain.categoriesrecommendations.dto.*;
import lombok.RequiredArgsConstructor;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoriesRecommendationsServiceImpl implements CategoriesRecommendationsService{

    @Override
    public CategoriesRecommendationsViewResponseDto CategoriesRecommendationsView(CategoriesRecommendationsViewRequestDto dto){
        char[] charArr = new char[4];
        if(dto.isMarried()) charArr[0]='1';
        else charArr[0]='0';
        if(dto.isHasPets()) charArr[1]='1';
        else charArr[1]='0';
        if(dto.isHasCar()) charArr[2]='1';
        else charArr[2]='0';
        if(dto.isHasChildren()) charArr[3]='1';
        else charArr[3]='0';
        String mainCategory = charArr.toString();
        if(dto.isStandard()){
            return CategoriesRecommendationsViewResponseDto.of(getRating(mainCategory));
        }else{
            return CategoriesRecommendationsViewResponseDto.of(getCounting(mainCategory));
        }
    }

    @Override
    public void CategoriesRecommendationsSave(Map<String, Object> params) {
        Map<String, String> map = new HashMap<>();
        char[] charArr = new char[4];
        int rating = 0;
        Iterator inputIter = params.keySet().iterator();
        while(inputIter.hasNext()){
            String key = inputIter.next().toString();
            if(key.equals("married")){
                if(params.get(key).toString().equals("true")) charArr[0]='1';
                else charArr[0]='0';
            }else if(key.equals("hasPets")){
                if(params.get(key).toString().equals("true")) charArr[1]='1';
                else charArr[1]='0';
            }else if(key.equals("hasCar")){
                if(params.get(key).toString().equals("true")) charArr[2]='1';
                else charArr[2]='0';
            }else if(key.equals("hasChildren")){
                if(params.get(key).toString().equals("true")) charArr[3]='1';
                else charArr[3]='0';
            }else if(key.equals("rating")){
                rating = Integer.parseInt(params.get(key).toString());
            }else{
                map.put(key, params.get(key).toString());
            }
        }
        String mainCategory = "";
        for(char c : charArr){
            mainCategory+=c;
        }
        SparkSession session = SparkSession.builder()
                .master("local")
                .appName("categories")
                .config("spark.mongodb.write.connection.uri", "mongodb://127.0.0.1/openapi.categories")
                .getOrCreate();
        try {
            String dataPath = "C:\\Users\\multicampus\\S07P22D110\\back\\demo\\src\\main\\resources\\data\\";
            File writeFile = new File(dataPath + "categories.json");
            FileOutputStream fileOutputStream = new FileOutputStream(writeFile);
            JSONObject valueJSON = new JSONObject();
            valueJSON.put("mainCategory", mainCategory);
            valueJSON.put("academy", 0);
            valueJSON.put("animalHospital", 0);
            valueJSON.put("animalBeauty", 0);
            valueJSON.put("carAccident", 0);
            valueJSON.put("childSafety", 0);
            valueJSON.put("concertHall", 0);
            valueJSON.put("crime", 0);
            valueJSON.put("drugStore", 0);
            valueJSON.put("electricvehiclecharging", 0);
            valueJSON.put("entertainment", 0);
            valueJSON.put("facilitiesForTheDisabled", 0);
            valueJSON.put("femaleSafety", 0);
            valueJSON.put("hospital", 0);
            valueJSON.put("kindergarden", 0);
            valueJSON.put("library", 0);
            valueJSON.put("mart", 0);
            valueJSON.put("park", 0);
            valueJSON.put("parkinglot", 0);
            valueJSON.put("publicTransportationUtilizationRate", 0);
            valueJSON.put("restaurant", 0);
            valueJSON.put("school", 0);
            valueJSON.put("shelter", 0);
            valueJSON.put("sportsFacilities", 0);
            valueJSON.put("station", 0);
            valueJSON.put("theater", 0);


            Iterator<String> iter = map.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                String value = map.get(key);
                int valueInt = 0;
                if (value.equals("high")) valueInt = 3;
                else if (value.equals("middle")) valueInt = 2;
                else if (value.equals("low")) valueInt = 1;
                valueJSON.put(key, valueInt);
            }

            valueJSON.put("rating", rating);
            fileOutputStream.write(valueJSON.toString().getBytes());
            fileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));

            Dataset<Row> dff = session.read().format("json").load(dataPath + "categories.json");
            dff.write().format("mongodb").mode("append").save();
            System.out.println("mongodb : finish");
        } catch (Exception e) {

        } finally {
            session.close();
        }
    }

    public List<CategoriesDto> getRating(String mainCategory){
        //서버
        //String dataPath = "/data/";
        //로컬
        String dataPath = "C:\\Users\\multicampus\\S07P22D110\\back\\demo\\src\\main\\resources\\data\\";
        List<CategoriesDto> returnRatingList = new ArrayList<>();
        try {
            File ratingFile = new File(dataPath + "rating.data");
            if (!ratingFile.exists()) { // 파일이 존재하지 않으면
                ratingFile.createNewFile(); // 신규생성
            }

            SparkSession session = SparkSession.builder()
                    .master("local")
                    .appName("categories")
                    .config("spark.mongodb.read.connection.uri", "mongodb://127.0.0.1/openapi.categories")
                    .getOrCreate();
            try {


                Dataset<Row> ds = session.read().format("mongodb").load();

                List<String> categoryList = new ArrayList<>();
                for (Row row : ds.select("mainCategory").collectAsList()) {
                    categoryList.add(row.get(0).toString());
                }
                List<String> ratingList = new ArrayList<>();
                for (Row row : ds.select("rating").collectAsList()) {
                    ratingList.add(row.get(0).toString());
                }
                for (String col : ds.columns()) {
                    if (col.equals("_id")) continue;
                    else if (col.equals("mainCategory")) continue;

                    FileOutputStream ratingFileOutputStream = new FileOutputStream(ratingFile);
                    int zeroCnt = 0;
                    List<Row> rowList = ds.select(col).collectAsList();
                    for (int i = 0; i < rowList.size(); i++) {
                        String s = rowList.get(i).get(0).toString();

                        if (s.equals("0")) zeroCnt++;

                        if (!categoryList.get(i).equals(mainCategory)) continue;
                        else if (rowList.get(i).get(0).toString().equals("0")) continue;

                        String rowString = categoryList.get(i) + " " + rowList.get(i).get(0).toString() + " " + ratingList.get(i);
                        ratingFileOutputStream.write(rowString.getBytes(StandardCharsets.UTF_8));
                        ratingFileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
                    }
                    if (zeroCnt == rowList.size()) continue;

                    JavaSparkContext sc = new JavaSparkContext(session.sparkContext());
                    JavaRDD<String> rawData = sc.textFile(dataPath + "rating.data");
                    JavaRDD<String[]> rawRatings = rawData.map(str -> str.split(" "));
                    JavaRDD<Rating> ratings = rawRatings.map(arr -> new Rating(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Double.parseDouble(arr[2])));
                    ratings.cache();
                    MatrixFactorizationModel model = ALS.train(JavaRDD.toRDD(ratings), 10, 2, 0.01);
                    int K = 3;
                    Rating[] topKRecs = model.recommendProducts(Integer.parseInt(mainCategory), K);
                    String s = "";
                    int num = topKRecs[0].product();
                    if (num == 1) s = "low";
                    else if (num == 2) s = "middle";
                    else s = "high";
                    returnRatingList.add(CategoriesDto.of(col, s));
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                session.close();

            }
        } catch (Exception e) {

        }
        return returnRatingList;
    }
    public List<CategoriesDto> getCounting(String mainCategory){
        //서버
        //String dataPath = "/data/";
        //로컬
        String dataPath = "C:\\Users\\multicampus\\S07P22D110\\back\\demo\\src\\main\\resources\\data\\";
        List<CategoriesDto> returnCountingList = new ArrayList<>();
        try {
            File countingFile = new File(dataPath + "counting.data");
            if (!countingFile.exists()) { // 파일이 존재하지 않으면
                countingFile.createNewFile(); // 신규생성
            }

            SparkSession session = SparkSession.builder()
                    .master("local")
                    .appName("categories")
                    .config("spark.mongodb.read.connection.uri", "mongodb://127.0.0.1/openapi.categories")
                    .getOrCreate();
            try {

                Dataset<Row> ds = session.read().format("mongodb").load();

                List<String> categoryList = new ArrayList<>();
                for (Row row : ds.select("mainCategory").collectAsList()) {
                    categoryList.add(row.get(0).toString());
                }
                List<String> ratingList = new ArrayList<>();
                for (Row row : ds.select("rating").collectAsList()) {
                    ratingList.add(row.get(0).toString());
                }
                for (String col : ds.columns()) {

                    if (col.equals("_id")) continue;
                    else if (col.equals("mainCategory")) continue;

                    FileOutputStream countFileOutputStream = new FileOutputStream(countingFile);
                    int zeroCnt = 0;
                    int oneCnt = 0;
                    int twoCnt = 0;
                    int threeCnt = 0;
                    List<Row> rowList = ds.select(col).collectAsList();
                    for (int i = 0; i < rowList.size(); i++) {
                        String s = rowList.get(i).get(0).toString();

                        if (s.equals("0")) zeroCnt++;
                        else if (s.equals("1")) oneCnt++;
                        else if (s.equals("2")) twoCnt++;
                        else if (s.equals("3")) threeCnt++;

                    }
                    if (zeroCnt == rowList.size()) continue;
                    countFileOutputStream.write(("1 " + "0 " + zeroCnt).getBytes(StandardCharsets.UTF_8));
                    countFileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
                    countFileOutputStream.write(("1 " + "1 " + oneCnt).getBytes(StandardCharsets.UTF_8));
                    countFileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
                    countFileOutputStream.write(("1 " + "2 " + twoCnt).getBytes(StandardCharsets.UTF_8));
                    countFileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
                    countFileOutputStream.write(("1 " + "3 " + threeCnt).getBytes(StandardCharsets.UTF_8));
                    countFileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));

                    JavaSparkContext sc = new JavaSparkContext(session.sparkContext());
                    JavaRDD<String> rawData = sc.textFile(dataPath + "counting.data");
                    JavaRDD<String[]> rawRatings = rawData.map(str -> str.split(" "));
                    JavaRDD<Rating> ratings = rawRatings.map(arr -> new Rating(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Double.parseDouble(arr[2])));
                    ratings.cache();
                    MatrixFactorizationModel model = ALS.train(JavaRDD.toRDD(ratings), 50, 2, 0.01);

                    int K = 3;
                    Rating[] topKRecs = model.recommendProducts(1, K);
                    String s = "";
                    int num = topKRecs[0].product();
                    if (num == 1) s = "low";
                    else if (num == 2) s = "middle";
                    else s = "high";
                    returnCountingList.add(CategoriesDto.of(col, s));

                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                session.close();

            }

        } catch (Exception e) {

        }
        return returnCountingList;
    }
}
