package com.salmanhaljido.demo.domain.concerthall.repository;

import com.salmanhaljido.demo.domain.concerthall.entity.ConcertHallDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ConcertHallDocRepository extends MongoRepository<ConcertHallDoc, String> {
    List<ConcertHallDoc> findAllBySdOrderByCount(String sd);
    List<ConcertHallDoc> findAllBySggOrderByCount(String sgg);
}
