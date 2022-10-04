package com.salmanhaljido.demo.domain.recommendation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class RecommendationCategory {
    private Map<String, Long> traffic;
//    private int trafficRanking;
    private double trafficScore;
    private Map<String, Long> calamity;
//    private int calamityRanking;
    private double calamityScore;
    private Map<String, Long> safety;
//    private int safetyRanking;
    private double safetyScore;
    private Map<String, Long> medical;
//    private int medicalRanking;
    private double medicalScore;
    private Map<String, Long> pet;
//    private int petRanking;
    private double petScore;
    private Map<String, Long> education;
//    private int educationRanking;
    private double educationScore;
    private Map<String, Long> culture;
//    private int cultureRanking;
    private double cultureScore;
    private Map<String, Long> life;
//    private int lifeRanking;
    private double lifeScore;

    public RecommendationCategory(){
        traffic = new HashMap<>();
        calamity = new HashMap<>();
        safety = new HashMap<>();
        medical = new HashMap<>();
        pet = new HashMap<>();
        education = new HashMap<>();
        culture = new HashMap<>();
        life = new HashMap<>();
    }
}
