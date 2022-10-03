package com.salmanhaljido.demo.domain.crime.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("crime")
@Getter
public class CrimeDoc{
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
