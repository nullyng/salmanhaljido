package com.salmanhaljido.demo.domain.recommendation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class Region {
    private String addr;
    private int ranking;
    private double score;
    private String code;
    private double lat;
    private double lng;
    private RecommendationCategory category = new RecommendationCategory();
    private List<Trading> tradings = new ArrayList<>();
    private List<Price> prices = new ArrayList<>();
}
