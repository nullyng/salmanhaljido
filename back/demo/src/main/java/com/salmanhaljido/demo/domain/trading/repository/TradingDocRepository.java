package com.salmanhaljido.demo.domain.trading.repository;

import com.salmanhaljido.demo.domain.trading.entity.TradingDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TradingDocRepository extends MongoRepository<TradingDoc, String > {
    List<TradingDoc> findAllBySd(String sd);
    List<TradingDoc> findAllBySgg(String sgg);
    TradingDoc findBySdAndSgg(String sd, String sgg);
}
