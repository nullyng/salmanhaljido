package com.salmanhaljido.demo.domain.park.repository;

import com.salmanhaljido.demo.domain.park.entity.ParkDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ParkDocRepository extends MongoRepository<ParkDoc, String> {
    List<ParkDoc> findAllBySdOrderByCount(String sd);
    List<ParkDoc> findAllBySggOrderByCount(String sgg);
}
