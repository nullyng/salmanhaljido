package com.salmanhaljido.demo.domain.childsafety.repository;

import com.salmanhaljido.demo.domain.childsafety.entity.ChildSafetyDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChildSafetyDocRepository extends MongoRepository<ChildSafetyDoc, String> {
    List<ChildSafetyDoc> findAllBySdOrderByCount(String sd);
    List<ChildSafetyDoc> findAllBySggOrderByCount(String sgg);
}
