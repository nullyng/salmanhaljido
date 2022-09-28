package com.salmanhaljido.demo.domain.categoriesrecommendations.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class CategoriesRecommendations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommendation_id")
    private Long id;

    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private String value;

    @Builder
    public CategoriesRecommendations(String categoryName, String value){
        this.categoryName=categoryName;
        this.value=value;
    }

}
