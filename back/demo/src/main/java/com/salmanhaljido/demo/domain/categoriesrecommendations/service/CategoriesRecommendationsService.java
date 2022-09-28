package com.salmanhaljido.demo.domain.categoriesrecommendations.service;

import com.salmanhaljido.demo.domain.categoriesrecommendations.dto.CategoriesRecommendationsViewRequestDto;
import com.salmanhaljido.demo.domain.categoriesrecommendations.dto.CategoriesRecommendationsViewResponseDto;

import java.util.Map;

public interface CategoriesRecommendationsService {
    CategoriesRecommendationsViewResponseDto CategoriesRecommendationsView(CategoriesRecommendationsViewRequestDto dto);
    void CategoriesRecommendationsSave(Map<String, Object> params);
}
