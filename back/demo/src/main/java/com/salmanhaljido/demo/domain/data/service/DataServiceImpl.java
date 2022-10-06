package com.salmanhaljido.demo.domain.data.service;


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
import com.salmanhaljido.demo.domain.restaurant.service.RestaurantService;
import com.salmanhaljido.demo.domain.school.service.SchoolService;
import com.salmanhaljido.demo.domain.shelter.service.ShelterService;
import com.salmanhaljido.demo.domain.sportsfacilities.service.SportsFacilitiesService;
import com.salmanhaljido.demo.domain.theater.service.TheaterService;
import com.salmanhaljido.demo.domain.trading.service.TradingService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {

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
    private final RestaurantService restaurantService;
    private final ShelterService shelterService;
    private final SchoolService schoolService;
    private final SportsFacilitiesService sportsFacilitiesService;
    private final TheaterService theaterService;
    private final TradingService tradingService;

    @Override
    @Scheduled(cron = "0 0 5 1/1 * ?")
    public void saveData(){
        try {
            academyService.getData();
            animalHospitalService.getData();
            animalSalonService.getData();
            carAccidentService.getData();
            childSafetyService.getData();
            concertHallService.getData();
            crimeService.getData();
            drugStoreService.getData();
            electricVehicleChargingService.getData();
            entertainmentService.getData();
            facilitiesForTheDisabledService.getData();
            hospitalService.getData();
            femaleSafetyService.getData();
            jeonseService.getData();
//            kinderGardenService.getData();
            libraryService.getData();
            martService.getData();
            parkService.getData();
//            restaurantService.getData();
            schoolService.getData();
            shelterService.getData();
            sportsFacilitiesService.getData();
            theaterService.getData();
            tradingService.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
