package com.salmanhaljido.demo.domain.school.repository;

import com.salmanhaljido.demo.domain.school.entity.SchoolDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SchoolDocRepository extends MongoRepository<SchoolDoc, String > {
    List<SchoolDoc> findAllBySdOrderByCount(String sd);
    List<SchoolDoc> findAllBySggOrderByCount(String sgg);
}
