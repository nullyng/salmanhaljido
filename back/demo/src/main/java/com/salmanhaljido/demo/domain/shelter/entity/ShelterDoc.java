package com.salmanhaljido.demo.domain.shelter.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("shelter")
@Getter
public class ShelterDoc{
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
