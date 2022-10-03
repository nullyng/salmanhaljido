package com.salmanhaljido.demo.domain.mart.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("mart")
@Getter
public class MartDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
