package com.salmanhaljido.demo.domain.recommendation.service;

import com.salmanhaljido.demo.domain.academy.entity.AcademyDoc;
import com.salmanhaljido.demo.domain.academy.repository.AcademyDocRepository;
import com.salmanhaljido.demo.domain.animalhospital.entity.AnimalHospitalDoc;
import com.salmanhaljido.demo.domain.animalhospital.repository.AnimalHospitalDocRepository;
import com.salmanhaljido.demo.domain.animalsalon.entity.AnimalSalonDoc;
import com.salmanhaljido.demo.domain.animalsalon.repository.AnimalSalonDocRepository;
import com.salmanhaljido.demo.domain.caraccident.entity.CarAccidentDoc;
import com.salmanhaljido.demo.domain.caraccident.repository.CarAccidentDocRepository;
import com.salmanhaljido.demo.domain.childsafety.entity.ChildSafetyDoc;
import com.salmanhaljido.demo.domain.childsafety.repository.ChildSafetyDocRepository;
import com.salmanhaljido.demo.domain.code.entity.GuGunCode;
import com.salmanhaljido.demo.domain.code.entity.SiDoCode;
import com.salmanhaljido.demo.domain.code.repository.GuGunCodeRepository;
import com.salmanhaljido.demo.domain.code.repository.SiDoCodeRepository;
import com.salmanhaljido.demo.domain.concerthall.entity.ConcertHallDoc;
import com.salmanhaljido.demo.domain.concerthall.repository.ConcertHallDocRepository;
import com.salmanhaljido.demo.domain.crime.entity.CrimeDoc;
import com.salmanhaljido.demo.domain.crime.repository.CrimeDocRepository;
import com.salmanhaljido.demo.domain.data.service.DataService;
import com.salmanhaljido.demo.domain.drugstore.entity.DrugStoreDoc;
import com.salmanhaljido.demo.domain.drugstore.repository.DrugStoreDocRepository;
import com.salmanhaljido.demo.domain.electricvehiclecharging.entity.ElectricVehicleChargingDoc;
import com.salmanhaljido.demo.domain.electricvehiclecharging.repository.ElectricVehicleChargingDocRepository;
import com.salmanhaljido.demo.domain.entertainment.entity.EntertainmentDoc;
import com.salmanhaljido.demo.domain.entertainment.repository.EntertainmentDocRepository;
import com.salmanhaljido.demo.domain.facilitiesforthedisabled.entity.FFDDoc;
import com.salmanhaljido.demo.domain.facilitiesforthedisabled.repository.FFDDocRepository;
import com.salmanhaljido.demo.domain.femalesafety.entity.FemaleSafetyDoc;
import com.salmanhaljido.demo.domain.femalesafety.repository.FemaleSafetyDocRepository;
import com.salmanhaljido.demo.domain.hospital.entity.HospitalDoc;
import com.salmanhaljido.demo.domain.hospital.repository.HospitalDocRepository;
import com.salmanhaljido.demo.domain.jeonse.entity.JeonseDoc;
import com.salmanhaljido.demo.domain.jeonse.repository.JeonseDocRepository;
import com.salmanhaljido.demo.domain.kindergarden.entity.KinderGardenDoc;
import com.salmanhaljido.demo.domain.kindergarden.repository.KinderGardenDocRepository;
import com.salmanhaljido.demo.domain.library.entity.LibraryDoc;
import com.salmanhaljido.demo.domain.library.repository.LibraryDocRepository;
import com.salmanhaljido.demo.domain.mart.entity.MartDoc;
import com.salmanhaljido.demo.domain.mart.repository.MartDocRepository;
import com.salmanhaljido.demo.domain.park.entity.ParkDoc;
import com.salmanhaljido.demo.domain.park.repository.ParkDocRepository;
import com.salmanhaljido.demo.domain.recommendation.dto.Price;
import com.salmanhaljido.demo.domain.recommendation.dto.RecommendationResponse;
import com.salmanhaljido.demo.domain.recommendation.dto.Region;
import com.salmanhaljido.demo.domain.recommendation.dto.Trading;
import com.salmanhaljido.demo.domain.school.entity.SchoolDoc;
import com.salmanhaljido.demo.domain.school.repository.SchoolDocRepository;
import com.salmanhaljido.demo.domain.shelter.entity.ShelterDoc;
import com.salmanhaljido.demo.domain.shelter.repository.ShelterDocRepository;
import com.salmanhaljido.demo.domain.sportsfacilities.entity.SportsFacilitiesDoc;
import com.salmanhaljido.demo.domain.sportsfacilities.repository.SportsFacilitiesDocRepository;
import com.salmanhaljido.demo.domain.theater.entity.TheaterDoc;
import com.salmanhaljido.demo.domain.theater.repository.TheaterDocRepository;
import com.salmanhaljido.demo.domain.trading.entity.TradingDoc;
import com.salmanhaljido.demo.domain.trading.repository.TradingDocRepository;
import com.salmanhaljido.demo.global.config.ServiceComponent;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
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
public class RecommendationServiceImpl implements RecommendationService{
    private final DataService dataService;
    private final SiDoCodeRepository siDoCodeRepository;
    private final GuGunCodeRepository guGunCodeRepository;

    private final ServiceComponent serviceComponent;

    private final AcademyDocRepository academyDocRepository;
    private final AnimalHospitalDocRepository animalHospitalDocRepository;
    private final AnimalSalonDocRepository animalSalonDocRepository;
    private final CarAccidentDocRepository carAccidentDocRepository;
    private final ChildSafetyDocRepository childSafetyDocRepository;
    private final ConcertHallDocRepository concertHallDocRepository;
    private final CrimeDocRepository crimeDocRepository;
    private final DrugStoreDocRepository drugStoreDocRepository;
    private final ElectricVehicleChargingDocRepository electricVehicleChargingDocRepository;
    private final EntertainmentDocRepository entertainmentDocRepository;
    private final FFDDocRepository ffdDocRepository;
    private final FemaleSafetyDocRepository femaleSafetyDocRepository;
    private final HospitalDocRepository hospitalDocRepository;
    private final JeonseDocRepository jeonseDocRepository;
    private final KinderGardenDocRepository kinderGardenDocRepository;
    private final LibraryDocRepository libraryDocRepository;
    private final MartDocRepository martDocRepository;
    private final ParkDocRepository parkDocRepository;
    private final SchoolDocRepository schoolDocRepository;
    private final ShelterDocRepository shelterDocRepository;
    private final SportsFacilitiesDocRepository sportsFacilitiesDocRepository;
    private final TheaterDocRepository theaterDocRepository;
    private final TradingDocRepository tradingDocRepository;

    String dataPath = "src/main/resources/data/";

    @Override
    public RecommendationResponse doResponse(Map<String, String> map) {
        return doResponse(null, map);
    }

//    http://localhost:8080/recommendations?code=4300000000&academy=high&animalsalon=high
//    http://localhost:8080/recommendations?code=4300000000&academy=mid&animalsalon=high
//    http://localhost:8080/recommendations?code=4300000000&academy=low&animalsalon=high&animalhospital=high&caraccident=high&childsafety=high

    @Override
    public RecommendationResponse doResponse(String code, Map<String, String> map) {

        RecommendationsSave(map);
        if(code==null){
            Map<String, Integer> valueMap = countValue(map);
            int weightValue = 0;
            weightValue += valueMap.get("high");
            weightValue += (valueMap.get("middle") * 2);
            weightValue += (valueMap.get("low") * 3);
            return responseAllCode(map, weightValue);
        }
        // map = category : value
        SiDoCode siDoCode = siDoCodeRepository.findById(code).get();

        int totalCategoryCount = 0;

        Map<String, Integer> valueMap = countValue(map);
        int weightValue = 0;
        weightValue += valueMap.get("high");
        weightValue += (valueMap.get("middle") * 2);
        weightValue += (valueMap.get("low") * 3);

        if(siDoCode == null) {
            GuGunCode guGunCode = guGunCodeRepository.findById(code).get();
            return responseGuGunCode(guGunCode, map);
        }
        return responseSiDoCode(siDoCode, map, weightValue);
    }

    private Map<String, Integer> countValue(Map<String, String> map){
        Map<String, Integer> result = new HashMap<>();
        result.put("high", 0);
        result.put("middle", 0);
        result.put("low", 0);

        for(String key : map.keySet()){
            String value = map.get(key);
            if(value.equals("high") || value.equals("middle") || value.equals("low"))
                result.put(value, result.get(value) + 1);
        }
        return result;
    }
    private RecommendationResponse responseAllCode(Map<String, String> map, int weighValue){

        RecommendationResponse recommendationResponse = new RecommendationResponse();

        Map<String, Double> recommendations = new HashMap<>();

        Map<String, Region> regionMap = new HashMap<>();

        if(!map.containsKey("academy")) map.put("academy", "zero");
        if(!map.containsKey("animalHospital")) map.put("animalHospital", "zero");
        if(!map.containsKey("animalBeauty")) map.put("animalBeauty", "zero");
        if(!map.containsKey("carAccident")) map.put("carAccident", "zero");
        if(!map.containsKey("childSafety")) map.put("childSafety", "zero");
        if(!map.containsKey("concertHall")) map.put("concertHall", "zero");
        if(!map.containsKey("crime")) map.put("crime", "zero");
        if(!map.containsKey("drugStore")) map.put("drugStore", "zero");
        if(!map.containsKey("electricVehicleCharging")) map.put("electricVehicleCharging", "zero");
        if(!map.containsKey("entertainment")) map.put("entertainment", "zero");
        if(!map.containsKey("facilitiesForTheDisabled")) map.put("facilitiesForTheDisabled", "zero");
        if(!map.containsKey("femaleSafety")) map.put("femaleSafety", "zero");
        if(!map.containsKey("hospital")) map.put("hospital", "zero");
        if(!map.containsKey("library")) map.put("library", "zero");
        if(!map.containsKey("mart")) map.put("mart", "zero");
        if(!map.containsKey("park")) map.put("park", "zero");
        if(!map.containsKey("school")) map.put("school", "zero");
        if(!map.containsKey("shelter")) map.put("shelter", "zero");
        if(!map.containsKey("sportsFacilities")) map.put("sportsFacilities", "zero");
        if(!map.containsKey("theater")) map.put("theater", "zero");

        List<SiDoCode> sidoList = siDoCodeRepository.findAll();
        for(SiDoCode s : sidoList) {
            String sd = s.getAddr();
            Region region = new Region();
            SiDoCode siDoCode = siDoCodeRepository.findSiDoCodeByAddr(sd);
            region.setCode(siDoCode.getCode());
            region.setAddr(siDoCode.getAddr());
                region.setLat(siDoCode.getLat());
                region.setLng(siDoCode.getLng());
            regionMap.put(sd, region);
        }
            for(String serviceKey : map.keySet()){

                Map<String, Long> totalCount = new HashMap<>();
                for(SiDoCode s : sidoList){
                    String sd = s.getAddr();

                // serviceKey = academy, animalhospital ...
                //System.out.println();
                if(serviceKey.equals("academy")){
                    List<AcademyDoc> academyDocList = academyDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < academyDocList.size(); idx++){
                        count+=academyDocList.get(idx).getCount();
                    }
                    totalCount.put(sd, count);



                    regionMap.get(sd).getCategory().getEducation().put("academy", count);
                }else if(serviceKey.equals("animalHospital")){
                    List<AnimalHospitalDoc> animalHospitalDocList = animalHospitalDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < animalHospitalDocList.size(); idx++){
                        count+=animalHospitalDocList.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                    regionMap.get(sd).getCategory().getPet().put("animalHospital", count);

                }else if(serviceKey.equals("animalBeauty")){
                    List<AnimalSalonDoc> animalSalonDocList = animalSalonDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < animalSalonDocList.size(); idx++){
                        count+=animalSalonDocList.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                    regionMap.get(sd).getCategory().getPet().put("animalBeauty", count);

                }else if(serviceKey.equals("carAccident")){
                    List<CarAccidentDoc> carAccidentDocsList = carAccidentDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < carAccidentDocsList.size(); idx++){
                        count+=carAccidentDocsList.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getSafety().put("carAccident", count);

                }else if(serviceKey.equals("childSafety")){
                    // start
                    List<ChildSafetyDoc> childSafetyDocList = childSafetyDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < childSafetyDocList.size(); idx++){
                        count+=childSafetyDocList.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getSafety().put("childSafety",count);

                    // end
                }else if(serviceKey.equals("concertHall")){
                    // start
                    List<ConcertHallDoc> concertHallDocList = concertHallDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < concertHallDocList.size(); idx++){
                        count+=concertHallDocList.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getCulture().put("concertHall", count);

                    // end
                }else if(serviceKey.equals("crime")){
                    // start
                    List<CrimeDoc> crimeDocList = crimeDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < crimeDocList.size(); idx++){
                        count+=crimeDocList.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getSafety().put(serviceKey, count);

                    // end
                }else if(serviceKey.equals("drugStore")){
                    // start
                    List<DrugStoreDoc> list = drugStoreDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < list.size(); idx++){
                        count+=list.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getMedical().put(serviceKey, count);

                    // end
                }else if(serviceKey.equals("electricVehicleCharging")){
                    // start
                    List<ElectricVehicleChargingDoc> list = electricVehicleChargingDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < list.size(); idx++){
                        count+=list.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getTraffic().put(serviceKey, count);

                    // end
                }else if(serviceKey.equals("entertainment")){
                    // start
                    List<EntertainmentDoc> list = entertainmentDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < list.size(); idx++){
                        count+=list.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getCulture().put(serviceKey, count);

                    // end
                }else if(serviceKey.equals("facilitiesForTheDisabled")){
                    // start
                    List<FFDDoc> list = ffdDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < list.size(); idx++){
                        count+=list.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getLife().put(serviceKey, count);

                    // end
                }else if(serviceKey.equals("femaleSafety")){
                    // start
                    List<FemaleSafetyDoc> list = femaleSafetyDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < list.size(); idx++){
                        count+=list.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getSafety().put(serviceKey, count);

                    // end
                }else if(serviceKey.equals("hospital")){
                    // start
                    List<HospitalDoc> list = hospitalDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < list.size(); idx++){
                        count+=list.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getMedical().put(serviceKey, count);

                    // end
                }else if(serviceKey.equals("jeonse")){
                /*
                ToDo Jeonse
                 */
                }else if(serviceKey.equals("library")){
                    // start
                    List<LibraryDoc> list = libraryDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < list.size(); idx++){
                        count+=list.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getLife().put(serviceKey, totalCount.get(sd));

                    // end
                }else if(serviceKey.equals("mart")){
                    // start
                    List<MartDoc> list = martDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < list.size(); idx++){
                        count+=list.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getLife().put(serviceKey, totalCount.get(sd));

                    // end
                }else if(serviceKey.equals("park")){
                    // start
                    List<ParkDoc> list = parkDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < list.size(); idx++){
                        count+=list.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getLife().put(serviceKey, totalCount.get(sd));

                    // end
                }else if(serviceKey.equals("school")){
                    // start
                    List<SchoolDoc> list = schoolDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < list.size(); idx++){
                        count+=list.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getEducation().put(serviceKey, totalCount.get(sd));

                    // end
                }else if(serviceKey.equals("shelter")){
                    // start
                    List<ShelterDoc> list = shelterDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < list.size(); idx++){
                        count+=list.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getCalamity().put(serviceKey, totalCount.get(sd));

                    // end
                }else if(serviceKey.equals("sportsFacilities")){
                    // start
                    List<SportsFacilitiesDoc> list = sportsFacilitiesDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < list.size(); idx++){
                        count+=list.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getCulture().put(serviceKey, totalCount.get(sd));

                    // end
                }else if(serviceKey.equals("theater")){
                    // start
                    List<TheaterDoc> list = theaterDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < list.size(); idx++){
                        count+=list.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                    regionMap.get(sd).getCategory().getCulture().put(serviceKey, totalCount.get(sd));

                    // end
                }else if(serviceKey.equals("trading")){
                /*
                ToDo trading
                 */
                }

                }
                if(map.get(serviceKey).equals("zero")) continue;

                List<Map.Entry<String, Long>> entryList = new LinkedList<>(totalCount.entrySet());
                entryList.sort(Map.Entry.comparingByValue());
                for(int ranking = entryList.size() - 1; ranking >= 0; ranking--){
                    Map.Entry<String, Long> entry = entryList.get(ranking);
                    getRecommendation(entry.getKey(), recommendations, map.get(serviceKey), (double) (entryList.size() - ranking) / weighValue);
                }
        }

        // make region ranking, score
        List<Map.Entry<String, Double>> entryList = new LinkedList<>(recommendations.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
        for(int ranking = 1; ranking <= entryList.size(); ranking++){
            Map.Entry<String, Double> entry = entryList.get(ranking - 1);
            Region region = regionMap.get(entry.getKey());
            region.setRanking(ranking);
            region.setScore(entry.getValue());
            String sd = region.getAddr();
            String sgg = "";

            TradingDoc tradingDoc = tradingDocRepository.findBySdAndSgg(sd, sgg);
            JeonseDoc jeonseDoc = jeonseDocRepository.findBySdAndSgg(sd, sgg);

            if(tradingDoc != null) {
                String[] tradingPrice = tradingDoc.getPrice().split(",");
                String[] tradingDate = tradingDoc.getDate().split(",");
                for(int i = 0; i < tradingDate.length; i++){
                    Trading trading = new Trading();
                    trading.setDate(tradingDate[i]);
                    trading.setPrice(tradingPrice[i]);
                    region.getTradings().add(trading);
                }
            }
            if(jeonseDoc != null) {
                String[] jeonsePrice = jeonseDoc.getPrice().split(",");
                String[] jeonseDate = jeonseDoc.getDate().split(",");
                for(int i = 0; i < jeonseDate.length; i++){
                    Price price = new Price();
                    price.setDate(jeonseDate[i]);
                    price.setValue(jeonsePrice[i]);
                    region.getPrices().add(price);
                }
            }
            recommendationResponse.getRegions().add(region);
        }
        makeSafetyCategoryScore(recommendationResponse);
        makePetCategoryScore(recommendationResponse);
        makeEducationCategoryScore(recommendationResponse);
        makeLifeCategoryScore(recommendationResponse);
        makeCultureCategoryScore(recommendationResponse);
        makeTrafficCategoryScore(recommendationResponse);
        makeCalamityCategoryScore(recommendationResponse);
        makeMedicalCategoryScore(recommendationResponse);
        return recommendationResponse;
    }

    private RecommendationResponse responseSiDoCode(SiDoCode siDoCode, Map<String, String> map, int weighValue){

        RecommendationResponse recommendationResponse = new RecommendationResponse();

        Map<String, Double> recommendations = new HashMap<>();

        Map<String, Region> regionMap = new HashMap<>();

        if(!map.containsKey("academy")) map.put("academy", "zero");
        if(!map.containsKey("animalHospital")) map.put("animalHospital", "zero");
        if(!map.containsKey("animalBeauty")) map.put("animalBeauty", "zero");
        if(!map.containsKey("carAccident")) map.put("carAccident", "zero");
        if(!map.containsKey("childSafety")) map.put("childSafety", "zero");
        if(!map.containsKey("concertHall")) map.put("concertHall", "zero");
        if(!map.containsKey("crime")) map.put("crime", "zero");
        if(!map.containsKey("drugStore")) map.put("drugStore", "zero");
        if(!map.containsKey("electricVehicleCharging")) map.put("electricVehicleCharging", "zero");
        if(!map.containsKey("entertainment")) map.put("entertainment", "zero");
        if(!map.containsKey("facilitiesForTheDisabled")) map.put("facilitiesForTheDisabled", "zero");
        if(!map.containsKey("femaleSafety")) map.put("femaleSafety", "zero");
        if(!map.containsKey("hospital")) map.put("hospital", "zero");
        if(!map.containsKey("library")) map.put("library", "zero");
        if(!map.containsKey("mart")) map.put("mart", "zero");
        if(!map.containsKey("park")) map.put("park", "zero");
        if(!map.containsKey("school")) map.put("school", "zero");
        if(!map.containsKey("shelter")) map.put("shelter", "zero");
        if(!map.containsKey("sportsFacilities")) map.put("sportsFacilities", "zero");
        if(!map.containsKey("theater")) map.put("theater", "zero");

        for(String serviceKey : map.keySet()){
            // serviceKey = academy, animalhospital ...
            String sd = siDoCode.getAddr();
            Map<String, Long> totalCount = new HashMap<>();
            if(serviceKey.equals("academy")){
                List<AcademyDoc> academyDocList = academyDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < academyDocList.size(); idx++){
                    AcademyDoc doc = academyDocList.get(idx);
                    String recommendationKey = doc.getSd() + " " +  doc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();

                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + doc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getEducation().put("academy", totalCount.get(recommendationKey));
                }
            }else if(serviceKey.equals("animalHospital")){
                List<AnimalHospitalDoc> animalHospitalDocList = animalHospitalDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < animalHospitalDocList.size(); idx++){
                    AnimalHospitalDoc animalHospitalDoc = animalHospitalDocList.get(idx);
                    String recommendationKey = animalHospitalDoc.getSd() + " " +  animalHospitalDoc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)) {
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();

                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + animalHospitalDoc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getPet().put("animalHospital", totalCount.get(recommendationKey));
                }
            }else if(serviceKey.equals("animalBeauty")){
                List<AnimalSalonDoc> animalSalonDocList = animalSalonDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < animalSalonDocList.size(); idx++){
                    AnimalSalonDoc animalSalonDoc = animalSalonDocList.get(idx);
                    String recommendationKey = animalSalonDoc.getSd() + " " +  animalSalonDoc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + animalSalonDoc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getPet().put("animalBeauty", totalCount.get(recommendationKey));
                }
            }else if(serviceKey.equals("carAccident")){
                List<CarAccidentDoc> carAccidentDocsList = carAccidentDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < carAccidentDocsList.size(); idx++){
                    CarAccidentDoc carAccidentDoc = carAccidentDocsList.get(idx);
                    String recommendationKey = carAccidentDoc.getSd() + " " +  carAccidentDoc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + carAccidentDoc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getSafety().put("carAccident", totalCount.get(recommendationKey));
                }
            }else if(serviceKey.equals("childSafety")){
                // start
                List<ChildSafetyDoc> childSafetyDocList = childSafetyDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < childSafetyDocList.size(); idx++){
                    ChildSafetyDoc childSafetyDoc = childSafetyDocList.get(idx);
                    String recommendationKey = childSafetyDoc.getSd() + " " +  childSafetyDoc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + childSafetyDoc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getSafety().put("childSafety", totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("concertHall")){
                // start
                List<ConcertHallDoc> concertHallDocList = concertHallDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < concertHallDocList.size(); idx++){
                    ConcertHallDoc concertHallDoc = concertHallDocList.get(idx);
                    String recommendationKey = concertHallDoc.getSd() + " " +  concertHallDoc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + concertHallDoc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getCulture().put("concertHall", totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("crime")){
                // start
                List<CrimeDoc> list = crimeDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    CrimeDoc crimeDoc = list.get(idx);
                    if(crimeDoc.getSgg().equals("")) continue;
                    String recommendationKey = crimeDoc.getSd() + " " +  crimeDoc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + crimeDoc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getSafety().put(serviceKey, totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("drugStore")){
                // start
                List<DrugStoreDoc> list = drugStoreDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    DrugStoreDoc drugStoreDoc = list.get(idx);
                    String recommendationKey = drugStoreDoc.getSd() + " " +  drugStoreDoc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + drugStoreDoc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getMedical().put(serviceKey, totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("electricVehicleCharging")){
                // start
                List<ElectricVehicleChargingDoc> list = electricVehicleChargingDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    ElectricVehicleChargingDoc electricVehicleChargingDoc = list.get(idx);
                    String recommendationKey = electricVehicleChargingDoc.getSd() + " " +  electricVehicleChargingDoc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + electricVehicleChargingDoc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getTraffic().put(serviceKey, totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("entertainment")){
                // start
                List<EntertainmentDoc> list = entertainmentDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    EntertainmentDoc doc = list.get(idx);
                    String recommendationKey = doc.getSd() + " " +  doc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + doc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getCulture().put(serviceKey, totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("facilitiesForTheDisabled")){
                // start
                List<FFDDoc> list = ffdDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    FFDDoc doc = list.get(idx);
                    String recommendationKey = doc.getSd() + " " +  doc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + doc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getLife().put(serviceKey, totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("femaleSafety")){
                // start
                List<FemaleSafetyDoc> list = femaleSafetyDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    FemaleSafetyDoc doc = list.get(idx);
                    String recommendationKey = doc.getSd() + " " +  doc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + doc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getSafety().put(serviceKey, totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("hospital")){
                // start
                List<HospitalDoc> list = hospitalDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    HospitalDoc doc = list.get(idx);
                    String recommendationKey = doc.getSd() + " " +  doc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + doc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getMedical().put(serviceKey, totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("jeonse")){
                /*
                ToDo Jeonse
                 */
            }else if(serviceKey.equals("kindergarden")){
                // start
                List<KinderGardenDoc> list = kinderGardenDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    KinderGardenDoc doc = list.get(idx);
                    String recommendationKey = doc.getSd() + " " +  doc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + doc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getEducation().put(serviceKey, totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("library")){
                // start
                List<LibraryDoc> list = libraryDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    LibraryDoc doc = list.get(idx);
                    String recommendationKey = doc.getSd() + " " +  doc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + doc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getLife().put(serviceKey, totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("mart")){
                // start
                List<MartDoc> list = martDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    MartDoc doc = list.get(idx);
                    String recommendationKey = doc.getSd() + " " +  doc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + doc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getLife().put(serviceKey, totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("park")){
                // start
                List<ParkDoc> list = parkDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    ParkDoc doc = list.get(idx);
                    String recommendationKey = doc.getSd() + " " +  doc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + doc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getLife().put(serviceKey, totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("school")){
                // start
                List<SchoolDoc> list = schoolDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    SchoolDoc doc = list.get(idx);
                    String recommendationKey = doc.getSd() + " " +  doc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + doc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getEducation().put(serviceKey, totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("shelter")){
                // start
                List<ShelterDoc> list = shelterDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    ShelterDoc doc = list.get(idx);
                    String recommendationKey = doc.getSd() + " " +  doc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + doc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getCalamity().put(serviceKey, totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("sportsFacilities")){
                // start
                List<SportsFacilitiesDoc> list = sportsFacilitiesDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    SportsFacilitiesDoc doc = list.get(idx);
                    String recommendationKey = doc.getSd() + " " +  doc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + doc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getCulture().put(serviceKey, totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("theater")){
                // start
                List<TheaterDoc> list = theaterDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    TheaterDoc doc = list.get(idx);
                    String recommendationKey = doc.getSd() + " " +  doc.getSgg();
                    GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                    if(guGunCode==null || guGunCode.getLat()==null) continue;
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + doc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getCulture().put(serviceKey, totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("trading")){
                /*
                ToDo trading
                 */
            }

            if(map.get(serviceKey).equals("zero")) continue;

            // make ranking of category
            List<Map.Entry<String, Long>> entryList = new LinkedList<>(totalCount.entrySet());
            entryList.sort(Map.Entry.comparingByValue());
            for(int ranking = entryList.size() - 1; ranking >= 0; ranking--){
                Map.Entry<String, Long> entry = entryList.get(ranking);
                getRecommendation(entry.getKey(), recommendations, map.get(serviceKey), (double) (entryList.size() - ranking) / weighValue);
            }

        }
        int jeonseLow = Integer.parseInt(map.get("jeonseLow"))*10;
        int jeonseHigh = Integer.parseInt(map.get("jeonseHigh"))*10;
        int tradingLow = Integer.parseInt(map.get("tradingLow"))*100000;
        int tradingHigh = Integer.parseInt(map.get("tradingHigh"))*100000;

        // make region ranking, score
        List<Map.Entry<String, Double>> entryList = new LinkedList<>(recommendations.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
        for(int ranking = 1; ranking <= entryList.size(); ranking++){
            Map.Entry<String, Double> entry = entryList.get(ranking - 1);
            Region region = regionMap.get(entry.getKey());
            region.setRanking(ranking);
            region.setScore(entry.getValue());
            int tempIdx = region.getAddr().indexOf(' ');
            String sd = region.getAddr().substring(0, tempIdx);
            String sgg = region.getAddr().substring(tempIdx + 1);

            TradingDoc tradingDoc = tradingDocRepository.findBySdAndSgg(sd, sgg);
            JeonseDoc jeonseDoc = jeonseDocRepository.findBySdAndSgg(sd, sgg);

            if(tradingDoc != null) {
                String[] tradingPrice = tradingDoc.getPrice().split(",");
                String[] tradingDate = tradingDoc.getDate().split(",");
                if(Integer.parseInt(tradingPrice[0]) < tradingLow || Integer.parseInt(tradingPrice[0]) > tradingHigh){
                    entryList.remove(ranking - 1);
                    ranking--;
                    continue;
                }
                for(int i = 0; i < tradingDate.length; i++){
                    Trading trading = new Trading();
                    trading.setDate(tradingDate[i]);
                    trading.setPrice(tradingPrice[i]);
                    region.getTradings().add(trading);
                }
            }
            if(jeonseDoc != null) {
                String[] jeonsePrice = jeonseDoc.getPrice().split(",");
                String[] jeonseDate = jeonseDoc.getDate().split(",");
                if(Integer.parseInt(jeonsePrice[0]) < jeonseLow || Integer.parseInt(jeonsePrice[0]) > jeonseHigh){
                    entryList.remove(ranking - 1);
                    ranking--;
                    continue;
                }
                for(int i = 0; i < jeonseDate.length; i++){
                    Price price = new Price();
                    price.setDate(jeonseDate[i]);
                    price.setValue(jeonsePrice[i]);
                    region.getPrices().add(price);
                }
            }
            recommendationResponse.getRegions().add(region);
        }
        makeSafetyCategoryScore(recommendationResponse);
        makePetCategoryScore(recommendationResponse);
        makeEducationCategoryScore(recommendationResponse);
        makeLifeCategoryScore(recommendationResponse);
        makeCultureCategoryScore(recommendationResponse);
        makeTrafficCategoryScore(recommendationResponse);
        makeCalamityCategoryScore(recommendationResponse);
        makeMedicalCategoryScore(recommendationResponse);
        return recommendationResponse;
    }


    /*
        traffic = new HashMap<>();
        calamity = new HashMap<>();
        safety = new HashMap<>();
        medical = new HashMap<>();
        pet = new HashMap<>();
        education = new HashMap<>();
        culture = new HashMap<>();
        life = new HashMap<>();
     */
    private void makeTrafficCategoryScore(RecommendationResponse recommendationResponse){
        Map<String, Long> electricVehicleChargingMap = new HashMap<>();

        Map<String, Double> trafficScoreMap = new HashMap<>();

        for(Region region : recommendationResponse.getRegions()){
            electricVehicleChargingMap.put(region.getAddr(), region.getCategory().getTraffic().getOrDefault("electricVehicleCharging", 0L));
        }

        List<Map.Entry<String, Long>> electricVehicleChargingList = new LinkedList<>(electricVehicleChargingMap.entrySet());
        electricVehicleChargingList.sort(Map.Entry.comparingByValue());
        for(int ranking = electricVehicleChargingList.size() - 1; ranking >= 0; ranking--){
            Map.Entry<String, Long> electricVehicleCharging = electricVehicleChargingList.get(ranking);

            String addr = electricVehicleCharging.getKey();
            double electricVehicleChargingScore = (double) 100 / 1 * (ranking + 1) / electricVehicleChargingList.size();
            if(!trafficScoreMap.containsKey(addr)){
                trafficScoreMap.put(addr, 0.0);
            }
            trafficScoreMap.put(addr, trafficScoreMap.get(addr) + electricVehicleChargingScore);
        }
        for(Region region : recommendationResponse.getRegions()){
            region.getCategory().setTrafficScore(trafficScoreMap.get(region.getAddr()));
        }
    }

    private void makeCalamityCategoryScore(RecommendationResponse recommendationResponse){
        Map<String, Long> shelterMap = new HashMap<>();

        Map<String, Double> calamityScoreMap = new HashMap<>();

        for(Region region : recommendationResponse.getRegions()){
            shelterMap.put(region.getAddr(), region.getCategory().getCalamity().getOrDefault("shelter", 0L));
        }

        List<Map.Entry<String, Long>> shelterList = new LinkedList<>(shelterMap.entrySet());
        shelterList.sort(Map.Entry.comparingByValue());
        for(int ranking = shelterList.size() - 1; ranking >= 0; ranking--){
            Map.Entry<String, Long> shelter = shelterList.get(ranking);

            String addr = shelter.getKey();
            double shelterScore = (double) 100 / 1 * (ranking + 1) / shelterList.size();
            if(!calamityScoreMap.containsKey(addr)){
                calamityScoreMap.put(addr, 0.0);
            }
            calamityScoreMap.put(addr, calamityScoreMap.get(addr) + shelterScore);
        }
        for(Region region : recommendationResponse.getRegions()){
            region.getCategory().setCalamityScore(calamityScoreMap.get(region.getAddr()));
        }
    }

    private void makeMedicalCategoryScore(RecommendationResponse recommendationResponse){
        /*
        drugStore
        hospital
         */
        Map<String, Long> drugStoreMap = new HashMap<>();
        Map<String, Long> hospitalMap = new HashMap<>();

        Map<String, Double> medicalScoreMap = new HashMap<>();

        for(Region region : recommendationResponse.getRegions()){
            drugStoreMap.put(region.getAddr(), region.getCategory().getMedical().getOrDefault("drugStore", 0L));
            hospitalMap.put(region.getAddr(), region.getCategory().getMedical().getOrDefault("hospital", 0L));
        }

        List<Map.Entry<String, Long>> drugStoreList = new LinkedList<>(drugStoreMap.entrySet());
        drugStoreList.sort(Map.Entry.comparingByValue());
        List<Map.Entry<String, Long>> hospitalList = new LinkedList<>(hospitalMap.entrySet());
        drugStoreList.sort(Map.Entry.comparingByValue());
        for(int ranking = drugStoreList.size() - 1; ranking >= 0; ranking--){
            Map.Entry<String, Long> drugStore = drugStoreList.get(ranking);
            Map.Entry<String, Long> hospital = drugStoreList.get(ranking);

            String addr = drugStore.getKey();
            double drugStoreScore = (double) 100 / 2 * (ranking + 1) / drugStoreList.size();
            if(!medicalScoreMap.containsKey(addr)){
                medicalScoreMap.put(addr, 0.0);
            }
            medicalScoreMap.put(addr, medicalScoreMap.get(addr) + drugStoreScore);

            addr = hospital.getKey();
            double hospitalScore = (double) 100 / 2 * (ranking + 1) / hospitalList.size();
            if(!medicalScoreMap.containsKey(addr)){
                medicalScoreMap.put(addr, 0.0);
            }
            medicalScoreMap.put(addr, medicalScoreMap.get(addr) + hospitalScore);
        }
        for(Region region : recommendationResponse.getRegions()){
            region.getCategory().setMedicalScore(medicalScoreMap.get(region.getAddr()));
        }
    }
    
    private void makePetCategoryScore(RecommendationResponse recommendationResponse){
        Map<String, Long> animalHospitalMap = new HashMap<>();
        Map<String, Long> animalBeautyMap = new HashMap<>();

        Map<String, Double> petScoreMap = new HashMap<>();

        for(Region region : recommendationResponse.getRegions()){
            animalHospitalMap.put(region.getAddr(), region.getCategory().getPet().getOrDefault("animalBeauty", 0L));
            animalBeautyMap.put(region.getAddr(), region.getCategory().getPet().getOrDefault("animalHospital", 0L));
        }

        List<Map.Entry<String, Long>> animalHospitalList = new LinkedList<>(animalHospitalMap.entrySet());
        animalHospitalList.sort(Map.Entry.comparingByValue());
        List<Map.Entry<String, Long>> animalBeautyList = new LinkedList<>(animalBeautyMap.entrySet());
        animalBeautyList.sort(Map.Entry.comparingByValue());
        for(int ranking = animalHospitalList.size() - 1; ranking >= 0; ranking--){
            Map.Entry<String, Long> animalHospital = animalHospitalList.get(ranking);
            Map.Entry<String, Long> animalBeauty = animalBeautyList.get(ranking);

            String addr = animalHospital.getKey();
            double animalHospitalScore = (double) 100 / 2 * (ranking + 1) / animalHospitalList.size();
            if(!petScoreMap.containsKey(addr)){
                petScoreMap.put(addr, 0.0);
            }
            petScoreMap.put(addr, petScoreMap.get(addr) + animalHospitalScore);

            addr = animalBeauty.getKey();
            double animalBeautyScore = (double) 100 / 2 * (ranking + 1) / animalBeautyList.size();
            if(!petScoreMap.containsKey(addr)){
                petScoreMap.put(addr, 0.0);
            }
            petScoreMap.put(addr, petScoreMap.get(addr) + animalBeautyScore);
        }
        for(Region region : recommendationResponse.getRegions()){
            region.getCategory().setPetScore(petScoreMap.get(region.getAddr()));
        }
    }
    private void makeEducationCategoryScore(RecommendationResponse recommendationResponse){
        Map<String, Long> schoolMap = new HashMap<>();
        Map<String, Long> academyMap = new HashMap<>();

        Map<String, Double> educationScoreMap = new HashMap<>();

        for(Region region : recommendationResponse.getRegions()){
            schoolMap.put(region.getAddr(), region.getCategory().getPet().getOrDefault("school", 0L));
            academyMap.put(region.getAddr(), region.getCategory().getPet().getOrDefault("academy", 0L));
        }

        List<Map.Entry<String, Long>> schoolList = new LinkedList<>(schoolMap.entrySet());
        schoolList.sort(Map.Entry.comparingByValue());
        List<Map.Entry<String, Long>> academyList = new LinkedList<>(academyMap.entrySet());
        academyList.sort(Map.Entry.comparingByValue());
        for(int ranking = schoolList.size() - 1; ranking >= 0; ranking--){
            Map.Entry<String, Long> school = schoolList.get(ranking);
            Map.Entry<String, Long> academy = academyList.get(ranking);

            String addr = school.getKey();
            double schoolScore = (double) 100 / 2 * (ranking + 1) / schoolList.size();
            if(!educationScoreMap.containsKey(addr)){
                educationScoreMap.put(addr, 0.0);
            }
            educationScoreMap.put(addr, educationScoreMap.get(addr) + schoolScore);

            addr = academy.getKey();
            double animalBeautyScore = (double) 100 / 2 * (ranking + 1) / academyList.size();
            if(!educationScoreMap.containsKey(addr)){
                educationScoreMap.put(addr, 0.0);
            }
            educationScoreMap.put(addr, educationScoreMap.get(addr) + animalBeautyScore);
        }
        for(Region region : recommendationResponse.getRegions()){
            region.getCategory().setEducationScore(educationScoreMap.get(region.getAddr()));
        }
    }
    private void makeSafetyCategoryScore(RecommendationResponse recommendationResponse){
        /*
        childSafety	:	2719
carAccident	:	93
crime	:	409122
femaleSafety	:	108
         */
        Map<String, Long> childSafetyMap = new HashMap<>();
        Map<String, Long> carAccidentMap = new HashMap<>();
        Map<String, Long> crimeMap = new HashMap<>();
        Map<String, Long> femaleSafetyMap = new HashMap<>();

        Map<String, Double> safetyScoreMap = new HashMap<>();

        for(Region region : recommendationResponse.getRegions()){
            childSafetyMap.put(region.getAddr(), region.getCategory().getCulture().getOrDefault("childSafety", 0L));
            carAccidentMap.put(region.getAddr(), region.getCategory().getCulture().getOrDefault("carAccident", 0L));
            crimeMap.put(region.getAddr(), region.getCategory().getCulture().getOrDefault("crime", 0L));
            femaleSafetyMap.put(region.getAddr(), region.getCategory().getCulture().getOrDefault("femaleSafety", 0L));
        }

        List<Map.Entry<String, Long>> childSafetyList = new LinkedList<>(childSafetyMap.entrySet());
        childSafetyList.sort(Map.Entry.comparingByValue());
        List<Map.Entry<String, Long>> carAccidentList = new LinkedList<>(carAccidentMap.entrySet());
        carAccidentList.sort(Map.Entry.comparingByValue());
        List<Map.Entry<String, Long>> crimeList = new LinkedList<>(crimeMap.entrySet());
        crimeList.sort(Map.Entry.comparingByValue());
        List<Map.Entry<String, Long>> femaleSafetyList = new LinkedList<>(femaleSafetyMap.entrySet());
        femaleSafetyList.sort(Map.Entry.comparingByValue());
        for(int ranking = childSafetyList.size() - 1; ranking >= 0; ranking--){
            Map.Entry<String, Long> childSafety = childSafetyList.get(ranking);
            Map.Entry<String, Long> carAccident = carAccidentList.get(ranking);
            Map.Entry<String, Long> crime = crimeList.get(ranking);
            Map.Entry<String, Long> femaleSafety = femaleSafetyList.get(ranking);

            String addr = childSafety.getKey();
            double childSafetyScore = (double) 100 / 4 * (ranking + 1) / childSafetyList.size();
            if(!safetyScoreMap.containsKey(addr)){
                safetyScoreMap.put(addr, 0.0);
            }
            safetyScoreMap.put(addr, safetyScoreMap.get(addr) + childSafetyScore);

            addr = carAccident.getKey();
            double carAccidentScore = (double) 100 / 4 * (ranking + 1) / carAccidentList.size();
            if(!safetyScoreMap.containsKey(addr)){
                safetyScoreMap.put(addr, 0.0);
            }
            safetyScoreMap.put(addr, safetyScoreMap.get(addr) + carAccidentScore);

            addr = crime.getKey();
            double crimeScore = (double) 100 / 4 * (ranking + 1) / crimeList.size();
            if(!safetyScoreMap.containsKey(addr)){
                safetyScoreMap.put(addr, 0.0);
            }
            safetyScoreMap.put(addr, safetyScoreMap.get(addr) + crimeScore);

            addr = femaleSafety.getKey();
            double femaleSafetyScore = (double) 100 / 4 * (ranking + 1) / femaleSafetyList.size();
            if(!safetyScoreMap.containsKey(addr)){
                safetyScoreMap.put(addr, 0.0);
            }
            safetyScoreMap.put(addr, safetyScoreMap.get(addr) + femaleSafetyScore);
        }
        for(Region region : recommendationResponse.getRegions()){
            region.getCategory().setSafetyScore(safetyScoreMap.get(region.getAddr()));
        }
    }
    private void makeLifeCategoryScore(RecommendationResponse recommendationResponse){
        /*
        library	:	561
        mart	:	365
        facilitiesForTheDisabled	:	23455
        park	:	4136
         */
        Map<String, Long> libraryMap = new HashMap<>();
        Map<String, Long> martMap = new HashMap<>();
        Map<String, Long> FFDDMap = new HashMap<>();
        Map<String, Long> parkMap = new HashMap<>();

        Map<String, Double> lifeScoreMap = new HashMap<>();

        for(Region region : recommendationResponse.getRegions()){
            libraryMap.put(region.getAddr(), region.getCategory().getCulture().getOrDefault("library", 0L));
            martMap.put(region.getAddr(), region.getCategory().getCulture().getOrDefault("mart", 0L));
            FFDDMap.put(region.getAddr(), region.getCategory().getCulture().getOrDefault("facilitiesForTheDisabled", 0L));
            parkMap.put(region.getAddr(), region.getCategory().getCulture().getOrDefault("park", 0L));
        }

        List<Map.Entry<String, Long>> libraryList = new LinkedList<>(libraryMap.entrySet());
        libraryList.sort(Map.Entry.comparingByValue());
        List<Map.Entry<String, Long>> martList = new LinkedList<>(martMap.entrySet());
        martList.sort(Map.Entry.comparingByValue());
        List<Map.Entry<String, Long>> FFDDList = new LinkedList<>(FFDDMap.entrySet());
        FFDDList.sort(Map.Entry.comparingByValue());
        List<Map.Entry<String, Long>> parkList = new LinkedList<>(parkMap.entrySet());
        parkList.sort(Map.Entry.comparingByValue());
        for(int ranking = libraryList.size() - 1; ranking >= 0; ranking--){
            Map.Entry<String, Long> library = libraryList.get(ranking);
            Map.Entry<String, Long> mart = martList.get(ranking);
            Map.Entry<String, Long> FFDD = FFDDList.get(ranking);
            Map.Entry<String, Long> park = parkList.get(ranking);

            String addr = library.getKey();
            double libraryScore = (double) 100 / 4 * (ranking + 1) / libraryList.size();
            if(!lifeScoreMap.containsKey(addr)){
                lifeScoreMap.put(addr, 0.0);
            }
            lifeScoreMap.put(addr, lifeScoreMap.get(addr) + libraryScore);

            addr = mart.getKey();
            double martScore = (double) 100 / 4 * (ranking + 1) / martList.size();
            if(!lifeScoreMap.containsKey(addr)){
                lifeScoreMap.put(addr, 0.0);
            }
            lifeScoreMap.put(addr, lifeScoreMap.get(addr) + martScore);

            addr = FFDD.getKey();
            double FFDDScore = (double) 100 / 4 * (ranking + 1) / FFDDList.size();
            if(!lifeScoreMap.containsKey(addr)){
                lifeScoreMap.put(addr, 0.0);
            }
            lifeScoreMap.put(addr, lifeScoreMap.get(addr) + FFDDScore);

            addr = park.getKey();
            double parkScore = (double) 100 / 4 * (ranking + 1) / parkList.size();
            if(!lifeScoreMap.containsKey(addr)){
                lifeScoreMap.put(addr, 0.0);
            }
            lifeScoreMap.put(addr, lifeScoreMap.get(addr) + parkScore);
        }
        for(Region region : recommendationResponse.getRegions()){
            region.getCategory().setLifeScore(lifeScoreMap.get(region.getAddr()));
        }
    }

    private void makeCultureCategoryScore(RecommendationResponse recommendationResponse){
        Map<String, Long> sportsFacilitiesMap = new HashMap<>();
        Map<String, Long> entertainmentMap = new HashMap<>();
        Map<String, Long> theaterMap = new HashMap<>();
        Map<String, Long> concertHallMap = new HashMap<>();

        Map<String, Double> cultureScoreMap = new HashMap<>();

        for(Region region : recommendationResponse.getRegions()){
            sportsFacilitiesMap.put(region.getAddr(), region.getCategory().getCulture().getOrDefault("sportsFacilities", 0L));
            entertainmentMap.put(region.getAddr(), region.getCategory().getCulture().getOrDefault("entertainment", 0L));
            theaterMap.put(region.getAddr(), region.getCategory().getCulture().getOrDefault("theater", 0L));
            concertHallMap.put(region.getAddr(), region.getCategory().getCulture().getOrDefault("concertHall", 0L));
        }

        List<Map.Entry<String, Long>> sportsFacilitiesList = new LinkedList<>(sportsFacilitiesMap.entrySet());
        sportsFacilitiesList.sort(Map.Entry.comparingByValue());
        List<Map.Entry<String, Long>> entertainmentList = new LinkedList<>(entertainmentMap.entrySet());
        entertainmentList.sort(Map.Entry.comparingByValue());
        List<Map.Entry<String, Long>> theaterList = new LinkedList<>(theaterMap.entrySet());
        theaterList.sort(Map.Entry.comparingByValue());
        List<Map.Entry<String, Long>> concertHallList = new LinkedList<>(concertHallMap.entrySet());
        concertHallList.sort(Map.Entry.comparingByValue());
        for(int ranking = sportsFacilitiesList.size() - 1; ranking >= 0; ranking--){
            Map.Entry<String, Long> sportsFacilities = sportsFacilitiesList.get(ranking);
            Map.Entry<String, Long> entertainment = entertainmentList.get(ranking);
            Map.Entry<String, Long> theater = theaterList.get(ranking);
            Map.Entry<String, Long> concertHall = concertHallList.get(ranking);

            String addr = sportsFacilities.getKey();
            double sportsFacilitiesScore = (double) 100 / 4 * (ranking + 1) / sportsFacilitiesList.size();
            if(!cultureScoreMap.containsKey(addr)){
                cultureScoreMap.put(addr, 0.0);
            }
            cultureScoreMap.put(addr, cultureScoreMap.get(addr) + sportsFacilitiesScore);

            addr = entertainment.getKey();
            double entertainmentScore = (double) 100 / 4 * (ranking + 1) / entertainmentList.size();
            if(!cultureScoreMap.containsKey(addr)){
                cultureScoreMap.put(addr, 0.0);
            }
            cultureScoreMap.put(addr, cultureScoreMap.get(addr) + entertainmentScore);

            addr = theater.getKey();
            double theaterScore = (double) 100 / 4 * (ranking + 1) / theaterList.size();
            if(!cultureScoreMap.containsKey(addr)){
                cultureScoreMap.put(addr, 0.0);
            }
            cultureScoreMap.put(addr, cultureScoreMap.get(addr) + theaterScore);

            addr = concertHall.getKey();
            double concertHallScore = (double) 100 / 4 * (ranking + 1) / concertHallList.size();
            if(!cultureScoreMap.containsKey(addr)){
                cultureScoreMap.put(addr, 0.0);
            }
            System.out.println(concertHall.getValue());
            cultureScoreMap.put(addr, cultureScoreMap.get(addr) + concertHallScore);
        }
        for(Region region : recommendationResponse.getRegions()){
            region.getCategory().setCultureScore(cultureScoreMap.get(region.getAddr()));
        }
    }

    private void getRecommendation(String recommendationKey, Map<String, Double> recommendations, String weight, double recommendationValue){
        if(!recommendations.containsKey(recommendationKey)) recommendations.put(recommendationKey, 0.0);
        if(weight.equals("low")){
            recommendations.put(recommendationKey,recommendations.get(recommendationKey) + recommendationValue);
        }else if(weight.equals("middle")){
            recommendations.put(recommendationKey, recommendations.get(recommendationKey) + (2 * recommendationValue));
        }else if(weight.equals("high")){ // high
            recommendations.put(recommendationKey, recommendations.get(recommendationKey) + (3 * recommendationValue));
        }
    }

    public void RecommendationsSave(Map<String, String> params){
        Map<String, String> map = new HashMap<>();
        char[] charArr = new char[4];
        Iterator inputIter = params.keySet().iterator();
        while(inputIter.hasNext()){
            String key = inputIter.next().toString();
            if(key.equals("married")){
                if(params.get(key).equals("true")) charArr[0]='1';
                else charArr[0]='0';
            }else if(key.equals("hasPets")){
                if(params.get(key).equals("true")) charArr[1]='1';
                else charArr[1]='0';
            }else if(key.equals("hasCar")){
                if(params.get(key).equals("true")) charArr[2]='1';
                else charArr[2]='0';
            }else if(key.equals("hasChildren")){
                if(params.get(key).equals("true")) charArr[3]='1';
                else charArr[3]='0';
            }else if(key.equals("jeonseLow") || key.equals("jeonseHigh")
            || key.equals("tradingLow") || key.equals("tradingHigh")){
            }else{
                map.put(key, params.get(key));
            }
        }
        String mainCategory = "";
        for(char c : charArr){
            mainCategory+=c;
        }
        if(mainCategory.equals("0000")) return;

        SparkSession session = SparkSession.builder()
                .master("local")
                .appName("categories")
                .config("spark.mongodb.write.connection.uri", "mongodb://admin:salmand110@j7d110.p.ssafy.io/openapi.categories_search?authSource=admin")
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
            valueJSON.put("electricVehicleCharging", 0);
            valueJSON.put("entertainment", 0);
            valueJSON.put("facilitiesForTheDisabled", 0);
            valueJSON.put("femaleSafety", 0);
            valueJSON.put("hospital", 0);
            valueJSON.put("library", 0);
            valueJSON.put("mart", 0);
            valueJSON.put("park", 0);
            valueJSON.put("school", 0);
            valueJSON.put("shelter", 0);
            valueJSON.put("sportsFacilities", 0);
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

            fileOutputStream.write(valueJSON.toString().getBytes());
            fileOutputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));

            Dataset<Row> dff = session.read().format("json").load(dataPath + "categories.json");
            dff.write().format("mongodb").mode("append").save();
        } catch (Exception e) {

        } finally {
            session.close();
        }
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
