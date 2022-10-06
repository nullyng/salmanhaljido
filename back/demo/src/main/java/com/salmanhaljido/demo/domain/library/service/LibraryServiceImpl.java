package com.salmanhaljido.demo.domain.library.service;

import lombok.RequiredArgsConstructor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
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

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService{
    private final String host = "api.data.go.kr";
    private final String path = "openapi/tn_pubr_public_lbrry_api";
    private final int numOfRows = 100;
    private final String type = "json";
    private final String serviceKey = "SY0wwUOUgR+XvLXazywXmEeMbGvaGqsDrAIjvacheY12NY0tXxrGd/DONoLyIa2eV6y0SVI4zxfqZZRECk8wIw==";
    private final String dataPath = "src/main/resources/data/";

    private URL createURL(int pageNo) throws MalformedURLException {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(host)
                .path(path)
                .queryParam("serviceKey", URLEncoder.encode(serviceKey, StandardCharsets.UTF_8))
                .queryParam("pageNo", pageNo)
                .queryParam("numOfRows", numOfRows)
                .queryParam("type", type)
                .build(false);
        return new URL(uriComponents.toString());
    }

    @Override
    public void getData() {
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(150);
        manager.setDefaultMaxPerRoute(5);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(10000)
                .setConnectTimeout(10000)
                .setSocketTimeout(10000)
                .build();

        CloseableHttpClient httpClient = HttpClientBuilder
                .create()
                .setConnectionManager(manager)
                .setDefaultRequestConfig(requestConfig)
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient);

        try {
            File file = new File(dataPath + "library.data");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            int pageNo = 0;
            int count = 0;
            int totalCount = 0;
            do {
                URL url = createURL(pageNo);
                RestTemplate restTemplate = new RestTemplate(factory);

                HttpEntity<String> response = restTemplate.exchange(url.toURI(), HttpMethod.GET, null, String.class);

                JSONParser jsonParser = new JSONParser();

                JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
                JSONObject body = (JSONObject) ((JSONObject) jsonParser.parse(jsonObject.get("response").toString())).get("body");
                totalCount = Integer.parseInt(body.get("totalCount").toString());
                JSONArray items = (JSONArray) jsonParser.parse(body.get("items").toString());
                for(Object obj : items){
                    JSONObject item = (JSONObject) jsonParser.parse(obj.toString());
                    String addr = item.get("rdnmadr").toString();
                    fileOutputStream.write(addr.getBytes(StandardCharsets.UTF_8));
                    fileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
                }
                count += items.size();
                pageNo++;
            } while (count < totalCount);

            SparkSession session = SparkSession.builder()
                    .master("local")
                    .appName("library")
                    .config("spark.mongodb.write.connection.uri", "mongodb://admin:salmand110@j7d110.p.ssafy.io/openapi.library?authSource=admin")
                    .getOrCreate();

            Dataset<Row> df = session.read().text(dataPath + "library.data");
            JavaRDD<Row> rdd = df.toJavaRDD();

            JavaRDD<String> rdds = rdd.map(line -> {
                String[] tokens = line.toString().split("\\[|\\s");
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

            File writeFile = new File(dataPath + "library_result.json");
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

            Dataset<Row> dff = session.read().format("json").load(dataPath + "library_result.json");
            dff.write().format("mongodb").mode("overwrite").save();
            session.close();
            System.out.println("Library : Finish");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
