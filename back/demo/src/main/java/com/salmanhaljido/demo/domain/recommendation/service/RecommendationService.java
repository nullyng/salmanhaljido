package com.salmanhaljido.demo.domain.recommendation.service;

import com.salmanhaljido.demo.domain.recommendation.dto.RecommendationResponse;

import java.util.Map;

public interface RecommendationService {
    RecommendationResponse doResponse(String code, Map<String, String> map);
    RecommendationResponse doResponse(Map<String, String> map);
}
