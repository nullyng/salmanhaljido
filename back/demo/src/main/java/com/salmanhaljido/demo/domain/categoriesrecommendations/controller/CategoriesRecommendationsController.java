package com.salmanhaljido.demo.domain.categoriesrecommendations.controller;

import com.salmanhaljido.demo.domain.categoriesrecommendations.dto.CategoriesRecommendationsViewRequestDto;
import com.salmanhaljido.demo.domain.categoriesrecommendations.dto.CategoriesRecommendationsViewResponseDto;
import com.salmanhaljido.demo.domain.categoriesrecommendations.service.CategoriesRecommendationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoriesRecommendationsController {

    private final CategoriesRecommendationsService categoriesRecommendationsService;
    @GetMapping("/recommendations")
    public ResponseEntity<CategoriesRecommendationsViewResponseDto> categoriesView(CategoriesRecommendationsViewRequestDto dto){
        return ResponseEntity.ok().body(categoriesRecommendationsService.CategoriesRecommendationsView(dto));
    }
    @GetMapping("/recommendations/like")
    public void categoriesSave(@RequestParam Map<String, Object> params){
        categoriesRecommendationsService.CategoriesRecommendationsSave(params);
    }

}
