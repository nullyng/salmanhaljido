package com.salmanhaljido.demo.domain.drugstore.repository;

import com.salmanhaljido.demo.domain.drugstore.entity.DrugStoreDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DrugStoreDocRepository extends MongoRepository<DrugStoreDoc, String> {
    List<DrugStoreDoc> findAllBySdOrderByCount(String sd);
    List<DrugStoreDoc> findAllBySggOrderByCount(String sgg);
}
