package com.salmanhaljido.demo.domain.library.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("library")
@Getter
public class LibraryDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
