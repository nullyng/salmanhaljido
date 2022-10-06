package com.salmanhaljido.demo.domain.categoriesrecommendations.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoriesRecommendationsViewResponseDto {
    private List<CategoriesDto> categoriesList;
    public static CategoriesRecommendationsViewResponseDto of(List<CategoriesDto> categoriesList){
        return CategoriesRecommendationsViewResponseDto.builder()
                .categoriesList(categoriesList)
                .build();
    }
}