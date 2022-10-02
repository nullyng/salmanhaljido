package com.salmanhaljido.demo.domain.sportsfacilities.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("sportsfacilities")
@Getter
public class SportsFacilitiesDoc {
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private Long count;
}
