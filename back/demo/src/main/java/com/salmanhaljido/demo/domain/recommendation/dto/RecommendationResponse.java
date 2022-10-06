package com.salmanhaljido.demo.domain.recommendation.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RecommendationResponse {
    List<Region> regions = new ArrayList<>();
}
