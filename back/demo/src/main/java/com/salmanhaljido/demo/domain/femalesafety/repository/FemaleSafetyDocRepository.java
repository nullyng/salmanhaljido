package com.salmanhaljido.demo.domain.femalesafety.repository;

import com.salmanhaljido.demo.domain.femalesafety.entity.FemaleSafetyDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FemaleSafetyDocRepository extends MongoRepository<FemaleSafetyDoc, String> {
    List<FemaleSafetyDoc> findAllBySdOrderByCount(String sg);
    List<FemaleSafetyDoc> findAllBySggOrderByCount(String sgg);
}
