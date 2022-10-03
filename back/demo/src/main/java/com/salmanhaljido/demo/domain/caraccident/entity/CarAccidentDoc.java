package com.salmanhaljido.demo.domain.caraccident.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("caraccident")
@Getter
public class CarAccidentDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
