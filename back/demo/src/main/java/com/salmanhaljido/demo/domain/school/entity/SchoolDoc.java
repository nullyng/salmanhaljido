package com.salmanhaljido.demo.domain.school.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("school")
@Getter
public class SchoolDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
