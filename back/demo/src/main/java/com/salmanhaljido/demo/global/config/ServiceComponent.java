package com.salmanhaljido.demo.global.config;

import com.salmanhaljido.demo.domain.academy.service.AcademyService;
import com.salmanhaljido.demo.domain.animalhospital.service.AnimalHospitalService;
import com.salmanhaljido.demo.domain.animalsalon.service.AnimalSalonService;
import com.salmanhaljido.demo.domain.caraccident.service.CarAccidentService;
import com.salmanhaljido.demo.domain.childsafety.service.ChildSafetyService;
import com.salmanhaljido.demo.domain.concerthall.service.ConcertHallService;
import com.salmanhaljido.demo.domain.crime.service.CrimeService;
import com.salmanhaljido.demo.domain.drugstore.service.DrugStoreService;
import com.salmanhaljido.demo.domain.electricvehiclecharging.service.ElectricVehicleChargingService;
import com.salmanhaljido.demo.domain.entertainment.service.EntertainmentService;
import com.salmanhaljido.demo.domain.facilitiesforthedisabled.service.FacilitiesForTheDisabledService;
import com.salmanhaljido.demo.domain.femalesafety.service.FemaleSafetyService;
import com.salmanhaljido.demo.domain.hospital.service.HospitalService;
import com.salmanhaljido.demo.domain.jeonse.service.JeonseService;
import com.salmanhaljido.demo.domain.kindergarden.service.KinderGardenService;
import com.salmanhaljido.demo.domain.library.service.LibraryService;
import com.salmanhaljido.demo.domain.mart.service.MartService;
import com.salmanhaljido.demo.domain.park.service.ParkService;
import com.salmanhaljido.demo.domain.school.service.SchoolService;
import com.salmanhaljido.demo.domain.shelter.service.ShelterService;
import com.salmanhaljido.demo.domain.sportsfacilities.service.SportsFacilitiesService;
import com.salmanhaljido.demo.domain.theater.service.TheaterService;
import com.salmanhaljido.demo.domain.trading.service.TradingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ServiceComponent {
    private final AcademyService academyService;
    private final AnimalHospitalService animalHospitalService;
    private final AnimalSalonService animalSalonService;
    private final CarAccidentService carAccidentService;
    private final ChildSafetyService childSafetyService;
    private final ConcertHallService concertHallService;
    private final CrimeService crimeService;
    private final DrugStoreService drugStoreService;
    private final ElectricVehicleChargingService electricVehicleChargingService;
    private final EntertainmentService entertainmentService;
    private final FacilitiesForTheDisabledService facilitiesForTheDisabledService;
    private final HospitalService hospitalService;
    private final FemaleSafetyService femaleSafetyService;
    private final JeonseService jeonseService;
    private final KinderGardenService kinderGardenService;
    private final LibraryService libraryService;
    private final MartService martService;
    private final ParkService parkService;
    private final SchoolService schoolService;
    private final ShelterService shelterService;
    private final SportsFacilitiesService sportsFacilitiesService;
    private final TheaterService theaterService;
    private final TradingService tradingService;

    private Map<String, Object> map = new HashMap<>();

    @PostConstruct
    public void init(){
        map.put("academy", academyService);
        map.put("animalHospital", animalHospitalService);
        map.put("animalBeauty", animalSalonService);
        map.put("carAccident", carAccidentService);
        map.put("childSafety",childSafetyService);
        map.put("concertHall", concertHallService);
        map.put("crime", crimeService);
        map.put("drugStore", drugStoreService);
        map.put("electricVehicleCharging", electricVehicleChargingService);
        map.put("entertainment", entertainmentService);
        map.put("facilitiesForTheDisabled", facilitiesForTheDisabledService);
        map.put("femaleSafety", femaleSafetyService);
        map.put("hospital", hospitalService);
        map.put("jeonse", jeonseService);
        map.put("kindergarden", kinderGardenService);
        map.put("library", libraryService);
        map.put("mart", martService);
        map.put("park", parkService);
        map.put("school", schoolService);
        map.put("shelter", shelterService);
        map.put("sportsFacilities", sportsFacilitiesService);
        map.put("theater", theaterService);
        map.put("trading", tradingService);
    }

    public Object getService(String key){
        return this.map.get(key);
    }
}
