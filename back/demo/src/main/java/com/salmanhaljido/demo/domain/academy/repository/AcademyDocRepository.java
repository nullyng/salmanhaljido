package com.salmanhaljido.demo.domain.academy.repository;

import com.salmanhaljido.demo.domain.academy.entity.AcademyDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AcademyDocRepository extends MongoRepository<AcademyDoc, String> {
    List<AcademyDoc> findAllBySdOrderByCount(String sd);
    List<AcademyDoc> findAllBySggOrderByCount(String sgg);
}
