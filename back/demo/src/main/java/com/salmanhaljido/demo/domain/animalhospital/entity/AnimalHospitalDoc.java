package com.salmanhaljido.demo.domain.animalhospital.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("animalhospital")
@Getter
@ToString
public class AnimalHospitalDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
