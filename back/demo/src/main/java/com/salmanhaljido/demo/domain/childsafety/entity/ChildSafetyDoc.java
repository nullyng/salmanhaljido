package com.salmanhaljido.demo.domain.childsafety.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("childsafety")
@Getter
public class ChildSafetyDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
