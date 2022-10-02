package com.salmanhaljido.demo.domain.mart.repository;

import com.salmanhaljido.demo.domain.mart.entity.MartDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MartDocRepository extends MongoRepository<MartDoc, String > {
    List<MartDoc> findAllBySdOrderByCount(String sd);
    List<MartDoc> findAllBySggOrderByCount(String sgg);
}
