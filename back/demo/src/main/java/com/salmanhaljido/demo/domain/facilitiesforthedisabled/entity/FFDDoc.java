package com.salmanhaljido.demo.domain.facilitiesforthedisabled.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("facilitiesforthedisabled")
@Getter
public class FFDDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
