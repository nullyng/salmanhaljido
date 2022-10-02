package com.salmanhaljido.demo.domain.entertainment.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("entertainment")
@Getter
public class EntertainmentDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
