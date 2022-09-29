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
import com.salmanhaljido.demo.domain.sportsfacilities.service.SportsFacilitiesService;
import com.salmanhaljido.demo.domain.theater.service.TheaterService;
import com.salmanhaljido.demo.domain.trading.service.TradingService;
import javax.annotation.PostConstruct;
import com.salmanhaljido.demo.global.scheduler.DataSaveScheduler;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {
    private SchedulerFactory schedulerFactory;
    private Scheduler scheduler;

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
    private final SchoolService schoolService;
    private final SportsFacilitiesService sportsFacilitiesService;
    private final TheaterService theaterService;
    private final TradingService tradingService;

    @Override
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
            kinderGardenService.getData();
            libraryService.getData();
            martService.getData();
            parkService.getData();
//            restaurantService.getData();
            schoolService.getData();
            sportsFacilitiesService.getData();
            theaterService.getData();
            tradingService.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //지우지마 스케줄러 미리 구현한거
        //createScheduler();
    }

    public void createScheduler() {
        try{
            schedulerFactory = new StdSchedulerFactory();
            scheduler = schedulerFactory.getScheduler();

            JobDetail jobDetail = JobBuilder.newJob(DataSaveScheduler.class)
                    .withIdentity("job", "job_group")
                    .build();
            CronScheduleBuilder cronSch = CronScheduleBuilder.cronSchedule(new CronExpression("0 0 0 1/1 * ? *"));
            CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger()
                            .withIdentity("cron_trigger", "crong_trigger_group")
                                    .withSchedule(cronSch)
                                            .build();

            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();

        }catch(Exception e){
        }
    }
}
