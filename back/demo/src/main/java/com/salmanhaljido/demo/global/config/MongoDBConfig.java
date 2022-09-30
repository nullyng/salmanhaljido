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
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MongoDBConfig {
    private SparkSession session;
    @PostConstruct
    public void init(){
        session = SparkSession.builder()
                .master("local")
                .appName("categories")
                .config("spark.mongodb.read.connection.uri", "mongodb://j7d110.p.ssafy.io/openapi.categories")
                .getOrCreate();
    }
    public SparkSession getSession(){
        return session;
    }
}
