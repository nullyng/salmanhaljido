package com.salmanhaljido.demo.domain.recommendation.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class RecommendationCategory {
    private Map<String, Long> traffic = new HashMap<>();
    private Map<String, Long> calamity = new HashMap<>();
    private Map<String, Long> safety = new HashMap<>();
    private Map<String, Long> medical = new HashMap<>();
    private Map<String, Long> pet = new HashMap<>();
    private Map<String, Long> education = new HashMap<>();
    private Map<String, Long> culture = new HashMap<>();
    private Map<String, Long> life = new HashMap<>();
}
