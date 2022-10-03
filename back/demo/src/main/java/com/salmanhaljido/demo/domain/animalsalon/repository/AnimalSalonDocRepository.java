package com.salmanhaljido.demo.domain.animalsalon.repository;

import com.salmanhaljido.demo.domain.animalsalon.entity.AnimalSalonDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AnimalSalonDocRepository extends MongoRepository<AnimalSalonDoc, String> {
    List<AnimalSalonDoc> findAllBySdOrderByCount(String sd);
    List<AnimalSalonDoc> findAllBySggOrderByCount(String sgg);
}
