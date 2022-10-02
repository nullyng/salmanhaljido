package com.salmanhaljido.demo.domain.femalesafety.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("femalesafety")
@Getter
public class FemaleSafetyDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
