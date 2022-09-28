package com.salmanhaljido.demo.domain.categoriesrecommendations.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriesDto {
    private String category;
    private String value;
    public static CategoriesDto of(String category, String value){
        return CategoriesDto.builder()
                .category(category)
                .value(value)
                .build();
    }
}
