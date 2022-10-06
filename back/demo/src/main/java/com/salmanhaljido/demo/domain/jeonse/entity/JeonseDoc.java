package com.salmanhaljido.demo.domain.jeonse.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("jeonse")
@Getter
public class JeonseDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private String date;
    private String price;
}
