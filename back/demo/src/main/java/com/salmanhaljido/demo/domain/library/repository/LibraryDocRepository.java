package com.salmanhaljido.demo.domain.library.repository;

import com.salmanhaljido.demo.domain.library.entity.LibraryDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LibraryDocRepository extends MongoRepository<LibraryDoc, String> {
    List<LibraryDoc> findAllBySdOrderByCount(String sd);
    List<LibraryDoc> findAllBySggOrderByCount(String sgg);
}
