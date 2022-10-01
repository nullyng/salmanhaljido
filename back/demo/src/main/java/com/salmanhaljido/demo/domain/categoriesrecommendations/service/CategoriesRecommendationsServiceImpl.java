package com.salmanhaljido.demo.domain.categoriesrecommendations.service;


import com.salmanhaljido.demo.domain.categoriesrecommendations.dto.*;
import lombok.RequiredArgsConstructor;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoriesRecommendationsServiceImpl implements CategoriesRecommendationsService{
    String dataPath = "src/main/resources/data/";
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
        String mainCategory = "";
        for(char c : charArr){
            mainCategory+=c;
        }
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
                .config("spark.mongodb.read.connection.uri", "mongodb://admin:salmand110@j7d110.p.ssafy.io/openapi.categories?authSource=admin")
                .getOrCreate();
        try {
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

        List<CategoriesDto> returnRatingList = new ArrayList<>();
        try {

            SparkSession session = SparkSession.builder()
                    .master("local")
                    .appName("categories")
                    .config("spark.mongodb.read.connection.uri", "mongodb://admin:salmand110@j7d110.p.ssafy.io/openapi.categories?authSource=admin")
                    .getOrCreate();
            try {


                Dataset<Row> ds = session.read().format("mongodb").load();

                List<String> categoryList = new ArrayList<>();
                for (Row row : ds.select("mainCategory").collectAsList()) {
                    System.out.println(row.toString().substring(1, 5));
                    categoryList.add(row.toString().substring(1, 5));
                }
                List<String> ratingList = new ArrayList<>();
                for (Row row : ds.select("rating").collectAsList()) {
                    ratingList.add(row.get(0).toString());
                }
                for (String col : ds.columns()) {
                    if (col.equals("_id")) continue;
                    else if (col.equals("mainCategory")) continue;
                    else if (col.equals("rating")) continue;

                    int cnt=0;
                    int zeroCnt = 0;
                    List<Row> rowList = ds.select(col).collectAsList();
                    List<Rating> rowDataSetList = new ArrayList<>();

                    for (int i = 0; i < rowList.size(); i++) {
                        String s = rowList.get(i).get(0).toString();
                        if (!categoryList.get(i).equals(mainCategory)) continue;
                        cnt++;
                        if (s.equals("0")) {
                            zeroCnt++;
                        }
                        double d = Double.parseDouble(ratingList.get(i));
                        int key = (int)Math.round(Double.parseDouble(s));
                        rowDataSetList.add(new Rating(key, d));
                    }
                    if (zeroCnt == cnt) continue;

                    Collections.sort(rowDataSetList, new Comparator<Rating>() {
                        @Override
                        public int compare(Rating o1, Rating o2) {
                            return o2.product - o1.product;
                        }
                    });

                    List<Rating> rowDataList =new ArrayList<>();

                    double sum=0;
                    int count=0;
                    int temp=rowDataSetList.get(0).product;
                    for(int i=0;i<rowDataSetList.size();i++){
                        System.out.println(rowDataSetList.get(i).rating + ", " + rowDataSetList.get(i).product);
                        if(temp != rowDataSetList.get(i).product){
                            if(col.equals("carAccident"))
                                System.out.println("test : " +temp + ", " + sum/count);
                            rowDataList.add(new Rating(temp, sum/count));
                            temp=rowDataSetList.get(i).product;
                            count=1;
                            sum=rowDataSetList.get(i).rating;
                        }else{
                            sum+=rowDataSetList.get(i).rating;
                            count++;
                            if(i==rowDataSetList.size()-1){
                                rowDataList.add(new Rating(temp, sum/count));
                            }
                        }
                    }
                    Collections.sort(rowDataList, new Comparator<Rating>() {
                        @Override
                        public int compare(Rating o1, Rating o2) {
                            if(Double.compare(o1.rating, o2.rating) == 0) return o2.product - o1.product;
                            else return Double.compare(o2.rating, o1.rating);
                        }
                    });


                    String s = "";
                    int num = rowDataList.get(0).product;
                    if( num==0) continue;
                    else if (num == 1) s = "low";
                    else if (num == 2) s = "middle";
                    else if(num==3)s = "high";
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

        List<CategoriesDto> returnCountingList = new ArrayList<>();
        try {

            SparkSession session = SparkSession.builder()
                    .master("local")
                    .appName("categories")
                    .config("spark.mongodb.read.connection.uri", "mongodb://admin:salmand110@j7d110.p.ssafy.io/openapi.categories?authSource=admin")
                    .getOrCreate();
            try {

                Dataset<Row> ds = session.read().format("mongodb").load();

                List<String> categoryList = new ArrayList<>();
                for (Row row : ds.select("mainCategory").collectAsList()) {
                    categoryList.add(row.toString().substring(1, 5));
                }
                List<String> ratingList = new ArrayList<>();
                for (Row row : ds.select("rating").collectAsList()) {
                    ratingList.add(row.get(0).toString());
                }
                for (String col : ds.columns()) {

                    if (col.equals("_id")) continue;
                    else if (col.equals("mainCategory")) continue;
                    else if (col.equals("rating")) continue;

                    int cnt=0;
                    int zeroCnt = 0;
                    int oneCnt = 0;
                    int twoCnt = 0;
                    int threeCnt = 0;
                    List<Row> rowList = ds.select(col).collectAsList();
                    for (int i = 0; i < rowList.size(); i++) {
                        String s = rowList.get(i).get(0).toString().substring(0, 1);
                        if (!categoryList.get(i).equals(mainCategory)) continue;
                        cnt++;
                        if (s.equals("0")) zeroCnt++;
                        else if (s.equals("1")) oneCnt++;
                        else if (s.equals("2")) twoCnt++;
                        else if (s.equals("3")) threeCnt++;

                    }
                    if (zeroCnt == cnt) continue;



                    List<Rating> rowDataList =new ArrayList<>();
                    rowDataList.add(new Rating(0, zeroCnt));
                    rowDataList.add(new Rating(1, oneCnt));
                    rowDataList.add(new Rating(2, twoCnt));
                    rowDataList.add(new Rating(3, threeCnt));

                    Collections.sort(rowDataList, new Comparator<Rating>() {
                        @Override
                        public int compare(Rating o1, Rating o2) {
                            if(Double.compare(o1.rating, o2.rating) == 0) return o2.product - o1.product;
                            else return Double.compare(o2.rating, o1.rating);
                        }
                    });

                    String s = "";
                    int num = rowDataList.get(0).product;
                    if( num==0) continue;
                    else if (num == 1) s = "low";
                    else if (num == 2) s = "middle";
                    else if(num==3)s = "high";
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
    public class Rating{
        int product;
        double rating;

        public Rating(int product, double rating) {
            this.product = product;
            this.rating = rating;
        }
    }
}
