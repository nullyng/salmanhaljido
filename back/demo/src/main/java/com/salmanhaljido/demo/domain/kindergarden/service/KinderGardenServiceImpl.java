package com.salmanhaljido.demo.domain.kindergarden.service;

import com.salmanhaljido.demo.domain.code.entity.GuGunCode;
import com.salmanhaljido.demo.domain.code.repository.GuGunCodeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KinderGardenServiceImpl implements KinderGardenService{

    private String Key = "0309d87aef0740bb9607b86279daf3a3";
    private String host = "e-childschoolinfo.moe.go.kr";
    private String path = "api/notice/basicInfo.do";
    private int pageCnt = 1000;
    private String dataPath = "src/main/resources/data/";

    private final GuGunCodeRepository guGunCodeRepository;

    private URL createURL(GuGunCode guGunCode, int currentPage) throws MalformedURLException {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(host)
                .path(path)
                .queryParam("key", Key)
                .queryParam("pageCnt", pageCnt)
                .queryParam("sidoCode", guGunCode.getCode().substring(0, 2))
                .queryParam("sggCode", guGunCode.getCode().substring(0, 5))
                .queryParam("currentPage", currentPage)
                .build(false);
        return new URL(uriComponents.toString());
    }

    @Override
    public void getData() throws Exception {
        File writeFile = new File(dataPath + "kindergarden_result.json");
        FileOutputStream fileOutputStream = new FileOutputStream(writeFile);
        List<GuGunCode> guGunCodeList = guGunCodeRepository.findAll();
        for(GuGunCode guGunCode : guGunCodeList){
            int currentPage = 1;
            URL url = createURL(guGunCode, currentPage);

            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<String> response = restTemplate.exchange(url.toURI(), HttpMethod.GET, null, String.class);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
            JSONArray kinderInfo = (JSONArray) jsonParser.parse(jsonObject.get("kinderInfo").toString());

            JSONObject result = new JSONObject();

            String[] token = guGunCode.getAddr().split(" ");
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
            result.put("sd", sd);
            result.put("sgg", sgg);
            result.put("count", kinderInfo.size());

            fileOutputStream.write(result.toString().getBytes(StandardCharsets.UTF_8));
            fileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
        }

        SparkSession session = SparkSession.builder()
                .master("local")
                .appName("kindergarden")
                .config("spark.mongodb.write.connection.uri", "mongodb://127.0.0.1/openapi.kindergarden")
                .getOrCreate();

        Dataset<Row> dff = session.read().format("json").load(dataPath + "kindergarden_result.json");
        dff.write().format("mongodb").mode("overwrite").save();
        session.close();
        System.out.println("KinderGarden : Finish");
    }
}
