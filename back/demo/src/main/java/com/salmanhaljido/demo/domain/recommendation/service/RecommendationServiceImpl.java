package com.salmanhaljido.demo.domain.recommendation.service;

import com.salmanhaljido.demo.domain.academy.entity.AcademyDoc;
import com.salmanhaljido.demo.domain.academy.repository.AcademyDocRepository;
import com.salmanhaljido.demo.domain.code.entity.GuGunCode;
import com.salmanhaljido.demo.domain.code.entity.SiDoCode;
import com.salmanhaljido.demo.domain.code.repository.GuGunCodeRepository;
import com.salmanhaljido.demo.domain.code.repository.SiDoCodeRepository;
import com.salmanhaljido.demo.domain.data.service.DataService;
import com.salmanhaljido.demo.domain.recommendation.dto.RecommendationResponse;
import com.salmanhaljido.demo.global.config.ServiceComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService{
    private final DataService dataService;
    private final SiDoCodeRepository siDoCodeRepository;
    private final GuGunCodeRepository guGunCodeRepository;

    private final ServiceComponent serviceComponent;

    private final MongoTemplate mongoTemplate;

    private final AcademyDocRepository academyDocRepository;

    @Override
    public RecommendationResponse doResponse(Map<String, String> map) {
        return doResponse(null, map);
    }

    // http://localhost:8080/recommendations?code=12312&staions=high
    // http://localhost:8080/recommendations?staions=high

    @Override
    public RecommendationResponse doResponse(String code, Map<String, String> map) {
        SiDoCode siDoCode = siDoCodeRepository.findById(code).get();

        int totalCategoryCount = map.size();
        Map<String, Integer> valueMap = countValue(map);
        int weightValue = 0;
        for(String key : valueMap.keySet()){
            weightValue += valueMap.get(key);
        }

        System.out.println("sg");
        List<AcademyDoc> sdList = academyDocRepository.findAllBySd("서울특별시");
        sdList.forEach(it -> {
            System.out.println(it);
        });
        System.out.println("sgg");
        List<AcademyDoc> sggList = academyDocRepository.findAllBySgg("중구");
        sggList.forEach(it -> {
            System.out.println(it);
        });


        if(siDoCode == null) {
            GuGunCode guGunCode = guGunCodeRepository.findById(code).get();
            return responseGuGunCode(guGunCode, map);
        }
        return responseSiDoCode(siDoCode, map);
    }

    private Map<String, Integer> countValue(Map<String, String> map){
        Map<String, Integer> result = new HashMap<>();
        result.put("high", 0);
        result.put("mid", 0);
        result.put("low", 0);

        for(String key : map.keySet()){
            if(key.equals("high") || key.equals("mid") || key.equals("low"))
                result.put(map.get(key), result.get(map.get(key)) + 1);
        }
        return result;
    }

    private RecommendationResponse responseSiDoCode(SiDoCode siDoCode, Map<String, String> map){
        return null;
    }

    private RecommendationResponse responseGuGunCode(GuGunCode guGunCode, Map<String, String> map){
        return null;
    }

//        @PostConstruct
//        void doSave(){
//            try {
//                dataService.saveData();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
}
