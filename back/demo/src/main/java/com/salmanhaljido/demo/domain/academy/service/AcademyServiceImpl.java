package com.salmanhaljido.demo.domain.academy.service;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class AcademyServiceImpl implements AcademyService {

    private String host = "open.neis.go.kr";
    private String path = "hub/acaInsTiInfo";
    private String KEY = "6872cf587a594fe4ae0298f21d56b60a";
    private String Type = "json";
    private String dataPath = "src/main/resources/data/";
    private String[] ATPT_OFCDC_SC_CODE_LIST = {"B10", "C10", "D10", "E10", "F10", "G10", "H10", "I10", "J10", "K10", "M10", "N10", "P10", "Q10", "R10", "S10", "T10"};

    private int pSize = 1000;

    private URL createURL(String ATPT_OFCDC_SC_CODE, int pIndex) throws MalformedURLException {
        UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https")
                .host(host)
                .path(path)
                .queryParam("KEY", URLEncoder.encode(KEY, StandardCharsets.UTF_8))
                .queryParam("pSize", pSize)
                .queryParam("Type", Type)
                .queryParam("pIndex", pIndex)
                .queryParam("ATPT_OFCDC_SC_CODE", ATPT_OFCDC_SC_CODE)
                .build(false);
        return new URL(uriComponents.toString());
    }

    @Override
    public void getData() throws Exception {
        File file = new File(dataPath + "academy.data");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        for(String ATPT_OFCDC_SC_CODE : ATPT_OFCDC_SC_CODE_LIST) {
            int pIndex = 1;
            int totalCount = 0;
            int count = 0;
            do {
                URL url = createURL(ATPT_OFCDC_SC_CODE, pIndex);
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<?> entity = new HttpEntity(headers);

                JSONParser jsonParser = new JSONParser();

                HttpEntity<String> response = restTemplate.exchange(url.toURI(), HttpMethod.GET, entity, String.class);

                JSONObject total = (JSONObject) jsonParser.parse(response.getBody());

                JSONArray acaInsTiInfo = (JSONArray) jsonParser.parse(total.get("acaInsTiInfo").toString());
                JSONObject head = (JSONObject) jsonParser.parse(acaInsTiInfo.get(0).toString());
                JSONArray heads = (JSONArray) jsonParser.parse(head.get("head").toString());
                JSONObject listTotalCount = (JSONObject) jsonParser.parse(heads.get(0).toString());
                totalCount = Integer.parseInt(listTotalCount.get("list_total_count").toString());
                JSONObject row = (JSONObject) jsonParser.parse(acaInsTiInfo.get(1).toString());
                JSONArray rows = (JSONArray) jsonParser.parse(row.get("row").toString());
                for(Object obj : rows){
                    JSONObject jsonObject = (JSONObject) jsonParser.parse(obj.toString());
                    String str = jsonObject.get("FA_RDNMA") == null ? "" : jsonObject.get("FA_RDNMA").toString() + (jsonObject.get("FA_RDNDA") == null ? "" : jsonObject.get("FA_RDNDA").toString());
                    fileOutputStream.write(str.getBytes(StandardCharsets.UTF_8));
                    fileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
                    count++;
                }
                pIndex++;
            }while (count < totalCount);
        }

        SparkSession session = SparkSession.builder()
                .master("local")
                .appName("academy")
                .config("spark.mongodb.write.connection.uri", "mongodb://127.0.0.1/openapi.academy")
                .getOrCreate();

        Dataset<Row> df = session.read().text(dataPath + "academy.data");
        JavaRDD<Row> rdd = df.toJavaRDD();

        JavaRDD<String> rdds = rdd.map(line -> {
            String[] tokens = line.toString().split("\\[|\\s|\\\\|\\(|\\)");
            if(tokens.length < 3) return null;
            if(tokens[1].endsWith("시")){
                if(tokens[2].endsWith("구") || tokens[2].endsWith("군")) return tokens[1] + " " + tokens[2];
            }else if(tokens[1].endsWith("도")){
                if(tokens[2].endsWith("시")){
                    if(tokens[3].endsWith("구") || tokens[3].endsWith("군")){
                        return tokens[1] + " " + tokens[2] + " " + tokens[3];
                    }
                    return tokens[1] + " " + tokens[2];
                }
            }
            return null;
        });

        File writeFile = new File(dataPath + "academy_result.json");
        fileOutputStream = new FileOutputStream(writeFile);
        Map<String, Long> map = rdds.countByValue();
        for(String str : map.keySet()){
            JSONObject value = new JSONObject();
            value.put(str, map.get(str));
            fileOutputStream.write(value.toString().getBytes());
            fileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
        }

        Dataset<Row> dff = session.read().format("json").load(dataPath + "academy_result.json");
        dff.write().format("mongodb").mode("overwrite").save();
        System.out.println("Academy : Finish");
    }
}
