package com.salmanhaljido.demo.domain.shelter.repository;

import com.salmanhaljido.demo.domain.shelter.entity.ShelterDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShelterDocRepository extends MongoRepository<ShelterDoc, String> {
    List<ShelterDoc> findAllBySdOrderByCount(String sd);
    List<ShelterDoc> findAllBySggOrderByCount(String sgg);
}
