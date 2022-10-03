package com.salmanhaljido.demo.domain.sportsfacilities.repository;

import com.salmanhaljido.demo.domain.sportsfacilities.entity.SportsFacilitiesDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SportsFacilitiesDocRepository extends MongoRepository<SportsFacilitiesDoc, String > {
    List<SportsFacilitiesDoc> findAllBySdOrderByCount(String sd);
    List<SportsFacilitiesDoc> findAllBySggOrderByCount(String sgg);
}
