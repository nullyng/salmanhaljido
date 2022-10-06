package com.salmanhaljido.demo.domain.recommendation.controller;

import com.salmanhaljido.demo.domain.recommendation.dto.RecommendationResponse;
import com.salmanhaljido.demo.domain.recommendation.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping
    public ResponseEntity<RecommendationResponse> getRecommendation(@RequestParam(value = "code", required = false) String code, @RequestParam Map<String, String> map){
        if(code == null) {
            return ResponseEntity.ok().body(recommendationService.doResponse(map));
        }
        map.remove("code");
        return ResponseEntity.ok().body(recommendationService.doResponse(code, map));
    }
}
