package com.salmanhaljido.demo.domain.electricvehiclecharging.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("evc")
@Getter
public class ElectricVehicleChargingDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
