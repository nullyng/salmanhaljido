package com.salmanhaljido.demo.domain.drugstore.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("drugstore")
@Getter
public class DrugStoreDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
