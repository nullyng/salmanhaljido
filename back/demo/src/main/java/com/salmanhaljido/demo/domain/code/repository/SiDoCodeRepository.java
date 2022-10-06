package com.salmanhaljido.demo.domain.code.repository;

import com.salmanhaljido.demo.domain.code.entity.SiDoCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SiDoCodeRepository extends JpaRepository<SiDoCode, String> {
    SiDoCode findSiDoCodeByAddr(String addr);
    List<SiDoCode> findAll();
}
