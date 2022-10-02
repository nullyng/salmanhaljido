package com.salmanhaljido.demo.domain.hospital.repository;

import com.salmanhaljido.demo.domain.hospital.entity.HospitalDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HospitalDocRepository extends MongoRepository<HospitalDoc, String> {
    List<HospitalDoc> findAllBySdOrderByCount(String sd);
    List<HospitalDoc> findAllBySggOrderByCount(String sgg);
}