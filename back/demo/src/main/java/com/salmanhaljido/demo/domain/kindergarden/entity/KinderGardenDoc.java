package com.salmanhaljido.demo.domain.kindergarden.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("kindergarden")
@Getter
public class KinderGardenDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
