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
import org.springframework.stereotype.Service;

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

    @Override
    public RecommendationResponse doResponse(Map<String, String> map) {
        return doResponse(null, map);
    }

//    http://localhost:8080/recommendations?code=4300000000&academy=high&animalsalon=high
//    http://localhost:8080/recommendations?code=4300000000&academy=mid&animalsalon=high
//    http://localhost:8080/recommendations?code=4300000000&academy=low&animalsalon=high&animalhospital=high&caraccident=high&childsafety=high

    @Override
    public RecommendationResponse doResponse(String code, Map<String, String> map) {
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
        if(!map.containsKey("animalhospital")) map.put("animalhospital", "zero");
        if(!map.containsKey("animalsalon")) map.put("animalsalon", "zero");
        if(!map.containsKey("caraccident")) map.put("caraccident", "zero");
        if(!map.containsKey("childsafety")) map.put("childsafety", "zero");
        if(!map.containsKey("concerthall")) map.put("concerthall", "zero");
        if(!map.containsKey("crime")) map.put("crime", "zero");
        if(!map.containsKey("drugstore")) map.put("drugstore", "zero");
        if(!map.containsKey("electricvehiclecharging")) map.put("electricvehiclecharging", "zero");
        if(!map.containsKey("entertainment")) map.put("entertainment", "zero");
        if(!map.containsKey("facilitiesforthedisabled")) map.put("facilitiesforthedisabled", "zero");
        if(!map.containsKey("femalesafety")) map.put("femalesafety", "zero");
        if(!map.containsKey("hospital")) map.put("hospital", "zero");
        //if(map.containsKey("kindergarden")) map.put("kindergarden", "zero");
        if(!map.containsKey("library")) map.put("library", "zero");
        if(!map.containsKey("mart")) map.put("mart", "zero");
        if(!map.containsKey("park")) map.put("park", "zero");
        if(!map.containsKey("school")) map.put("school", "zero");
        if(!map.containsKey("shelter")) map.put("shelter", "zero");
        if(!map.containsKey("sportsfacilities")) map.put("sportsfacilities", "zero");
        if(!map.containsKey("theater")) map.put("theater", "zero");

        List<SiDoCode> sidoList = siDoCodeRepository.findAll();
        Map<String, Long> sidoCount = new HashMap<>();
        for(SiDoCode s : sidoList) {
            String sd = s.getAddr();
            Region region = new Region();
            SiDoCode siDoCode = siDoCodeRepository.findSiDoCodeByAddr(sd);
            region.setCode(siDoCode.getCode());
            region.setAddr(siDoCode.getAddr());
            region.setLat(1);
            region.setLng(1);
//                region.setLat(siDoCode.getLat());
//                region.setLng(siDoCode.getLng());
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
                }else if(serviceKey.equals("animalhospital")){
                    List<AnimalHospitalDoc> animalHospitalDocList = animalHospitalDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < animalHospitalDocList.size(); idx++){
                        count+=animalHospitalDocList.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                    regionMap.get(sd).getCategory().getPet().put("animalhospital", count);

                }else if(serviceKey.equals("animalsalon")){
                    List<AnimalSalonDoc> animalSalonDocList = animalSalonDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < animalSalonDocList.size(); idx++){
                        count+=animalSalonDocList.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                    regionMap.get(sd).getCategory().getPet().put("animalsalon", count);

                }else if(serviceKey.equals("caraccident")){
                    List<CarAccidentDoc> carAccidentDocsList = carAccidentDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < carAccidentDocsList.size(); idx++){
                        count+=carAccidentDocsList.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getSafety().put("caraccident", count);

                }else if(serviceKey.equals("childsafety")){
                    // start
                    List<ChildSafetyDoc> childSafetyDocList = childSafetyDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < childSafetyDocList.size(); idx++){
                        count+=childSafetyDocList.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getSafety().put("childsafety",count);

                    // end
                }else if(serviceKey.equals("concerthall")){
                    // start
                    List<ConcertHallDoc> concertHallDocList = concertHallDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < concertHallDocList.size(); idx++){
                        count+=concertHallDocList.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getCulture().put("concerthall", count);

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
                }else if(serviceKey.equals("drugstore")){
                    // start
                    List<DrugStoreDoc> list = drugStoreDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < list.size(); idx++){
                        count+=list.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getMedical().put(serviceKey, count);

                    // end
                }else if(serviceKey.equals("electricvehiclecharging")){
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
                }else if(serviceKey.equals("facilitiesforthedisabled")){
                    // start
                    List<FFDDoc> list = ffdDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < list.size(); idx++){
                        count+=list.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getLife().put(serviceKey, count);

                    // end
                }else if(serviceKey.equals("femalesafety")){
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
                }else if(serviceKey.equals("kindergarden")){
                    // start
                    List<KinderGardenDoc> list = kinderGardenDocRepository.findAllBySdOrderByCount(sd);
                    Long count = 0L;
                    for(int idx = 0; idx < list.size(); idx++){
                        count+=list.get(idx).getCount();
                    }
                    totalCount.put(sd, count);


                        regionMap.get(sd).getCategory().getEducation().put(serviceKey, totalCount.get(sd));

                    // end
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
                }else if(serviceKey.equals("sportsfacilities")){
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
//            int tempIdx = region.getAddr().indexOf(' ');
//            String sd = region.getAddr().substring(0, tempIdx);
//            String sgg = region.getAddr().substring(tempIdx + 1);
//
//            TradingDoc tradingDoc = tradingDocRepository.findBySdAndSgg(sd, sgg);
//            JeonseDoc jeonseDoc = jeonseDocRepository.findBySdAndSgg(sd, sgg);
//
//            if(tradingDoc != null) {
//                String[] tradingPrice = tradingDoc.getPrice().split(",");
//                String[] tradingDate = tradingDoc.getDate().split(",");
//                for(int i = 0; i < tradingDate.length; i++){
//                    Trading trading = new Trading();
//                    trading.setDate(tradingDate[i]);
//                    trading.setPrice(tradingPrice[i]);
//                    region.getTradings().add(trading);
//                }
//            }
//            if(jeonseDoc != null) {
//                String[] jeonsePrice = jeonseDoc.getPrice().split(",");
//                String[] jeonseDate = jeonseDoc.getDate().split(",");
//                for(int i = 0; i < jeonseDate.length; i++){
//                    Price price = new Price();
//                    price.setDate(jeonseDate[i]);
//                    price.setValue(jeonsePrice[i]);
//                    region.getPrices().add(price);
//                }
//            }
            recommendationResponse.getRegions().add(region);
        }
        return recommendationResponse;
    }

    private RecommendationResponse responseSiDoCode(SiDoCode siDoCode, Map<String, String> map, int weighValue){

        RecommendationResponse recommendationResponse = new RecommendationResponse();

        Map<String, Double> recommendations = new HashMap<>();

        Map<String, Region> regionMap = new HashMap<>();

        if(!map.containsKey("academy")) map.put("academy", "zero");
        if(!map.containsKey("animalhospital")) map.put("animalhospital", "zero");
        if(!map.containsKey("animalsalon")) map.put("animalsalon", "zero");
        if(!map.containsKey("caraccident")) map.put("caraccident", "zero");
        if(!map.containsKey("childsafety")) map.put("childsafety", "zero");
        if(!map.containsKey("concerthall")) map.put("concerthall", "zero");
        if(!map.containsKey("crime")) map.put("crime", "zero");
        if(!map.containsKey("drugstore")) map.put("drugstore", "zero");
        if(!map.containsKey("electricvehiclecharging")) map.put("electricvehiclecharging", "zero");
        if(!map.containsKey("entertainment")) map.put("entertainment", "zero");
        if(!map.containsKey("facilitiesforthedisabled")) map.put("facilitiesforthedisabled", "zero");
        if(!map.containsKey("femalesafety")) map.put("femalesafety", "zero");
        if(!map.containsKey("hospital")) map.put("hospital", "zero");
        //if(map.containsKey("kindergarden")) map.put("kindergarden", "zero");
        if(!map.containsKey("library")) map.put("library", "zero");
        if(!map.containsKey("mart")) map.put("mart", "zero");
        if(!map.containsKey("park")) map.put("park", "zero");
        if(!map.containsKey("school")) map.put("school", "zero");
        if(!map.containsKey("shelter")) map.put("shelter", "zero");
        if(!map.containsKey("sportsfacilities")) map.put("sportsfacilities", "zero");
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
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
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
            }else if(serviceKey.equals("animalhospital")){
                List<AnimalHospitalDoc> animalHospitalDocList = animalHospitalDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < animalHospitalDocList.size(); idx++){
                    AnimalHospitalDoc animalHospitalDoc = animalHospitalDocList.get(idx);
                    String recommendationKey = animalHospitalDoc.getSd() + " " +  animalHospitalDoc.getSgg();
                    if(!totalCount.containsKey(recommendationKey)) {
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + animalHospitalDoc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getPet().put("animalhospital", totalCount.get(recommendationKey));
                }
            }else if(serviceKey.equals("animalsalon")){
                List<AnimalSalonDoc> animalSalonDocList = animalSalonDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < animalSalonDocList.size(); idx++){
                    AnimalSalonDoc animalSalonDoc = animalSalonDocList.get(idx);
                    String recommendationKey = animalSalonDoc.getSd() + " " +  animalSalonDoc.getSgg();
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + animalSalonDoc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getPet().put("animalsalon", totalCount.get(recommendationKey));
                }
            }else if(serviceKey.equals("caraccident")){
                List<CarAccidentDoc> carAccidentDocsList = carAccidentDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < carAccidentDocsList.size(); idx++){
                    CarAccidentDoc carAccidentDoc = carAccidentDocsList.get(idx);
                    String recommendationKey = carAccidentDoc.getSd() + " " +  carAccidentDoc.getSgg();
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + carAccidentDoc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getSafety().put("caraccident", totalCount.get(recommendationKey));
                }
            }else if(serviceKey.equals("childsafety")){
                // start
                List<ChildSafetyDoc> childSafetyDocList = childSafetyDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < childSafetyDocList.size(); idx++){
                    ChildSafetyDoc childSafetyDoc = childSafetyDocList.get(idx);
                    String recommendationKey = childSafetyDoc.getSd() + " " +  childSafetyDoc.getSgg();
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + childSafetyDoc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getSafety().put("childsafety", totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("concerthall")){
                // start
                List<ConcertHallDoc> concertHallDocList = concertHallDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < concertHallDocList.size(); idx++){
                    ConcertHallDoc concertHallDoc = concertHallDocList.get(idx);
                    String recommendationKey = concertHallDoc.getSd() + " " +  concertHallDoc.getSgg();
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
                        region.setCode(guGunCode.getCode());
                        region.setAddr(guGunCode.getAddr());
                        region.setLat(guGunCode.getLat());
                        region.setLng(guGunCode.getLng());
                        regionMap.put(recommendationKey, region);
                    }
                    totalCount.put(recommendationKey, totalCount.get(recommendationKey) + concertHallDoc.getCount());
                }
                for(String recommendationKey : totalCount.keySet()){
                    regionMap.get(recommendationKey).getCategory().getCulture().put("concerthall", totalCount.get(recommendationKey));
                }
                // end
            }else if(serviceKey.equals("crime")){
                // start
                List<CrimeDoc> list = crimeDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    CrimeDoc crimeDoc = list.get(idx);
                    if(crimeDoc.getSgg().equals("")) continue;
                    String recommendationKey = crimeDoc.getSd() + " " +  crimeDoc.getSgg();
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
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
            }else if(serviceKey.equals("drugstore")){
                // start
                List<DrugStoreDoc> list = drugStoreDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    DrugStoreDoc drugStoreDoc = list.get(idx);
                    String recommendationKey = drugStoreDoc.getSd() + " " +  drugStoreDoc.getSgg();
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
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
            }else if(serviceKey.equals("electricvehiclecharging")){
                // start
                List<ElectricVehicleChargingDoc> list = electricVehicleChargingDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    ElectricVehicleChargingDoc electricVehicleChargingDoc = list.get(idx);
                    String recommendationKey = electricVehicleChargingDoc.getSd() + " " +  electricVehicleChargingDoc.getSgg();
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
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
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
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
            }else if(serviceKey.equals("facilitiesforthedisabled")){
                // start
                List<FFDDoc> list = ffdDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    FFDDoc doc = list.get(idx);
                    String recommendationKey = doc.getSd() + " " +  doc.getSgg();
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
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
            }else if(serviceKey.equals("femalesafety")){
                // start
                List<FemaleSafetyDoc> list = femaleSafetyDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    FemaleSafetyDoc doc = list.get(idx);
                    String recommendationKey = doc.getSd() + " " +  doc.getSgg();
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
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
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
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
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
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
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
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
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
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
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
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
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
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
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
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
            }else if(serviceKey.equals("sportsfacilities")){
                // start
                List<SportsFacilitiesDoc> list = sportsFacilitiesDocRepository.findAllBySdOrderByCount(sd);
                for(int idx = 0; idx < list.size(); idx++){
                    SportsFacilitiesDoc doc = list.get(idx);
                    String recommendationKey = doc.getSd() + " " +  doc.getSgg();
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
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
                    if(!totalCount.containsKey(recommendationKey)){
                        totalCount.put(recommendationKey, 0L);
                    }
                    if(!regionMap.containsKey(recommendationKey)){
                        Region region = new Region();
                        GuGunCode guGunCode = guGunCodeRepository.findByAddr(recommendationKey);
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
        return recommendationResponse;
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
