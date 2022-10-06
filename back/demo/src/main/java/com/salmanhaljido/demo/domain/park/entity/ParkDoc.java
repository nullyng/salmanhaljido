package com.salmanhaljido.demo.domain.park.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("park")
@Getter
public class ParkDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
