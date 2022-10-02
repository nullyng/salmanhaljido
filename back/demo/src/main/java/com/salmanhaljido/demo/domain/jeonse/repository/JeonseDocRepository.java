package com.salmanhaljido.demo.domain.jeonse.repository;

import com.salmanhaljido.demo.domain.jeonse.entity.JeonseDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JeonseDocRepository extends MongoRepository<JeonseDoc, String> {
    List<JeonseDoc> findAllBySd(String sd);
    List<JeonseDoc> findAllBySgg(String sgg);
    JeonseDoc findBySdAndSgg(String sd, String sgg);
}
