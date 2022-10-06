package com.salmanhaljido.demo.domain.concerthall.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("concerthall")
@Getter
public class ConcertHallDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
