package com.salmanhaljido.demo.domain.crime.repository;

import com.salmanhaljido.demo.domain.crime.entity.CrimeDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CrimeDocRepository extends MongoRepository<CrimeDoc, String> {
    List<CrimeDoc> findAllBySdOrderByCount(String sd);
    List<CrimeDoc> findAllBySggOrderByCount(String sgg);
}
