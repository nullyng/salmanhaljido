package com.salmanhaljido.demo.domain.animalsalon.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("animalsalon")
@Getter
@ToString
public class AnimalSalonDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
