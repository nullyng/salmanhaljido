package com.salmanhaljido.demo.domain.categoriesrecommendations.dto;

import lombok.Data;

@Data
public class CategoriesRecommendationsViewRequestDto {
    private boolean married;
    private boolean hasPets;
    private boolean hasCar;
    private boolean hasChildren;
    private boolean standard;
}
