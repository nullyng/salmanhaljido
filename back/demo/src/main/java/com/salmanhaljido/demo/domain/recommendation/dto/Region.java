package com.salmanhaljido.demo.domain.recommendation.dto;

import java.util.List;

public class Region {
    private String addr;
    private int ranking;
    private double score;
    private String code;
    private double lat;
    private double lng;
    private RecommendationCategory category;
    private List<Trading> tradings;
    private List<Price> prices;
}
