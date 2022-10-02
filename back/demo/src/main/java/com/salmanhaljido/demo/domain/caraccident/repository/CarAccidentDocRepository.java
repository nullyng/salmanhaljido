package com.salmanhaljido.demo.domain.caraccident.repository;

import com.salmanhaljido.demo.domain.caraccident.entity.CarAccidentDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarAccidentDocRepository extends MongoRepository<CarAccidentDoc, String> {
    List<CarAccidentDoc> findAllBySdOrderByCount(String sd);
    List<CarAccidentDoc> findAllBySggOrderByCount(String sgg);
}
