package com.salmanhaljido.demo.domain.electricvehiclecharging.repository;

import com.salmanhaljido.demo.domain.electricvehiclecharging.entity.ElectricVehicleChargingDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ElectricVehicleChargingDocRepository extends MongoRepository<ElectricVehicleChargingDoc, String> {
    List<ElectricVehicleChargingDoc> findAllBySdOrderByCount(String sd);
    List<ElectricVehicleChargingDoc> findAllBySggOrderByCount(String sgg);
}
