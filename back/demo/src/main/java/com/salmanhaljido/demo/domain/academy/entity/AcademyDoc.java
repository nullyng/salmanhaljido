package com.salmanhaljido.demo.domain.academy.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("academy")
@Getter
@ToString
public class AcademyDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}

