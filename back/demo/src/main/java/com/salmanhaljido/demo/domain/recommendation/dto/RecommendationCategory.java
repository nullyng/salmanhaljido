package com.salmanhaljido.demo.domain.recommendation.dto;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class RecommendationCategory {
    private Map<String, Long> traffic;
    private Map<String, Long> calamity;
    private Map<String, Long> safety;
    private Map<String, Long> medical;
    private Map<String, Long> pet;
    private Map<String, Long> education;
    private Map<String, Long> culture;
    private Map<String, Long> life;

    public RecommendationCategory(){
        traffic = new HashMap<>();
        traffic.put("electricVehicleCharging", 0L);
        calamity = new HashMap<>();
        calamity.put("shelter", 0L);
        safety = new HashMap<>();
        safety.put("carAccident", 0L);
        safety.put("childSafety", 0L);
        safety.put("crime", 0L);
        safety.put("femaleSafety", 0L);
        medical = new HashMap<>();
        medical.put("drugStore", 0L);
        medical.put("hospital", 0L);
        pet = new HashMap<>();
        pet.put("animalHospital", 0L);
        pet.put("animalSalon", 0L);
        education = new HashMap<>();
        education.put("academy", 0L);
        education.put("school", 0L);
        culture = new HashMap<>();
        culture.put("entertainment", 0L);
        culture.put("concertHall", 0L);
        culture.put("sportsFacilities", 0L);
        culture.put("theater", 0L);
        life = new HashMap<>();
        life.put("facilitiesForTheDisabled", 0L);
        life.put("library", 0L);
        life.put("mart", 0L);
        life.put("park", 0L);
    }
}
