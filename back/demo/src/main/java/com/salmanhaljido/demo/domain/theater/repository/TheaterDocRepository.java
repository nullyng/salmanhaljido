package com.salmanhaljido.demo.domain.theater.repository;

import com.salmanhaljido.demo.domain.theater.entity.TheaterDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TheaterDocRepository extends MongoRepository<TheaterDoc, String > {
    List<TheaterDoc> findAllBySdOrderByCount(String sd);
    List<TheaterDoc> findAllBySggOrderByCount(String sgg);
}
