package com.salmanhaljido.demo.domain.trading.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("trading")
@Getter
public class TradingDoc{
    @Id
    private String _id;
    private String sd;
    private String sgg;
    private String date;
    private String price;
}
