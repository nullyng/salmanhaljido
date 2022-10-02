package com.salmanhaljido.demo.domain.kindergarden.repository;

import com.salmanhaljido.demo.domain.kindergarden.entity.KinderGardenDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface KinderGardenDocRepository extends MongoRepository<KinderGardenDoc, String> {
    List<KinderGardenDoc> findAllBySdOrderByCount(String sd);
    List<KinderGardenDoc> findAllBySggOrderByCount(String sgg);
}
