package com.salmanhaljido.demo.domain.hospital.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("hospital")
@Getter
public class HospitalDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
