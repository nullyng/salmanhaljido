package com.salmanhaljido.demo.domain.data.service;


import com.salmanhaljido.demo.domain.academy.service.AcademyServiceImpl;
import com.salmanhaljido.demo.domain.animalhospital.service.AnimalHospitalServiceImpl;
import com.salmanhaljido.demo.domain.animalsalon.service.AnimalSalonServiceImpl;
import com.salmanhaljido.demo.domain.caraccident.service.CarAccidentServiceImpl;
import com.salmanhaljido.demo.domain.childsafety.service.ChildSafetyServiceImpl;
import com.salmanhaljido.demo.domain.concerthall.service.ConcertHallServiceImpl;
import com.salmanhaljido.demo.domain.crime.service.CrimeServiceImpl;
import com.salmanhaljido.demo.domain.data.exception.*;
import com.salmanhaljido.demo.domain.drugstore.service.DrugStoreServiceImpl;
import com.salmanhaljido.demo.domain.electricvehiclecharging.service.ElectricVehicleChargingImpl;
import com.salmanhaljido.demo.domain.entertainment.service.EntertainmentServiceImpl;
import com.salmanhaljido.demo.domain.facilitiesforthedisabled.service.FacilitiesForTheDisabledServiceImpl;
import com.salmanhaljido.demo.domain.femalesafety.service.FemaleSafetyServiceImpl;
import com.salmanhaljido.demo.domain.hospital.service.HospitalServiceImpl;
import com.salmanhaljido.demo.domain.jeonse.service.JeonseServiceImpl;
import com.salmanhaljido.demo.domain.kindergarden.service.KinderGardenServiceImpl;
import com.salmanhaljido.demo.domain.library.service.LibraryServiceImpl;
import com.salmanhaljido.demo.domain.mart.service.MartServiceImpl;
import com.salmanhaljido.demo.domain.park.service.ParkServiceImpl;
import com.salmanhaljido.demo.domain.restaurant.service.RestaurantServiceImpl;
import com.salmanhaljido.demo.domain.school.service.SchoolServiceImpl;
import com.salmanhaljido.demo.domain.shelter.service.ShelterServiceImpl;
import com.salmanhaljido.demo.domain.sportsfacilities.service.SportsFacilitiesServiceImpl;
import com.salmanhaljido.demo.domain.theater.service.TheaterServiceImpl;
import com.salmanhaljido.demo.domain.trading.service.TradingServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class DataServiceImpl implements DataService {


    @Override
    public void saveData() throws Exception{
        try{
            AcademyServiceImpl data = new AcademyServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new AcademySaveException();
        }
        try{
            AnimalHospitalServiceImpl data = new AnimalHospitalServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new AnimalHospitalSaveException();
        }

        try{
            AnimalSalonServiceImpl data = new AnimalSalonServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new AnimalSalonSaveException();
        }
        try{
            CarAccidentServiceImpl data = new CarAccidentServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new CarAccidentSaveException();
        }
        try{
            ChildSafetyServiceImpl data = new ChildSafetyServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new ChildSafetySaveException();
        }
        try{
            ConcertHallServiceImpl data = new ConcertHallServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new ConcertHallSaveException();
        }
        try{
            CrimeServiceImpl data = new CrimeServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new CrimeSaveException();
        }
        try{
            DrugStoreServiceImpl data = new DrugStoreServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new DrugStoreSaveException();
        }
        try{
            ElectricVehicleChargingImpl data = new ElectricVehicleChargingImpl();
            data.getData();
        }catch(Exception e){
            throw new ElectricVehicleChargingSaveException();
        }
        try{
            EntertainmentServiceImpl data = new EntertainmentServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new EntertainmentSaveException();
        }
        try{
            FacilitiesForTheDisabledServiceImpl data = new FacilitiesForTheDisabledServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new FacilitiesForTheDisabledSaveException();
        }
        try{
            FemaleSafetyServiceImpl data = new FemaleSafetyServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new FemaleSafetySaveException();
        }
        try{
            HospitalServiceImpl data = new HospitalServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new HospitalSaveException();
        }
        try{
            JeonseServiceImpl data = new JeonseServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new JeonseSaveException();
        }
        try{
//            KinderGardenServiceImpl data = new KinderGardenServiceImpl();
//            data.getData();
        }catch(Exception e){
            throw new KinderGardenSaveException();
        }
        try{
            LibraryServiceImpl data = new LibraryServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new LibrarySaveException();
        }
        try{
            MartServiceImpl data = new MartServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new MartSaveException();
        }
        try{
            ParkServiceImpl data = new ParkServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new ParkSaveException();
        }
        try{
            //RestaurantServiceImpl data = new RestaurantServiceImpl();
            //data.getData();
        }catch(Exception e){
            throw new RestaurantSaveException();
        }
        try{
            SchoolServiceImpl data = new SchoolServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new SchoolSaveException();
        }
        try{
            ShelterServiceImpl data = new ShelterServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new ShelterSaveException();
        }
        try{
            SportsFacilitiesServiceImpl data = new SportsFacilitiesServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new SportsFacilitiesSaveException();
        }
        try{
            TheaterServiceImpl data = new TheaterServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new TheaterSaveException();
        }
        try{
            TradingServiceImpl data = new TradingServiceImpl();
            data.getData();
        }catch(Exception e){
            throw new TradingSaveException();
        }
        System.out.println("Data Save Finish");

    }
}
