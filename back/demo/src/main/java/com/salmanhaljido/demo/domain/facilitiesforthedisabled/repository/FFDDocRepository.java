package com.salmanhaljido.demo.domain.facilitiesforthedisabled.repository;

import com.salmanhaljido.demo.domain.facilitiesforthedisabled.entity.FFDDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FFDDocRepository extends MongoRepository<FFDDoc, String> {
    List<FFDDoc> findAllBySdOrderByCount(String sd);
    List<FFDDoc> findAllBySggOrderByCount(String sgg);
}
