package com.salmanhaljido.demo.domain.animalhospital.repository;

import com.salmanhaljido.demo.domain.animalhospital.entity.AnimalHospitalDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AnimalHospitalDocRepository extends MongoRepository<AnimalHospitalDoc, String> {
    List<AnimalHospitalDoc> findAllBySdOrderByCount(String sd);
    List<AnimalHospitalDoc> findAllBySggOrderByCount(String sgg);
}
