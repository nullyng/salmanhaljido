package com.salmanhaljido.demo.domain.entertainment.repository;

import com.salmanhaljido.demo.domain.entertainment.entity.EntertainmentDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EntertainmentDocRepository extends MongoRepository<EntertainmentDoc, String> {
    List<EntertainmentDoc> findAllBySdOrderByCount(String sd);
    List<EntertainmentDoc> findAllBySggOrderByCount(String sgg);
}
