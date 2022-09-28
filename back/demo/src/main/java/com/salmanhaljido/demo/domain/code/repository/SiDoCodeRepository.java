package com.salmanhaljido.demo.domain.code.repository;

import com.salmanhaljido.demo.domain.code.entity.SiDoCode;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SiDoCodeRepository extends JpaRepository<SiDoCode, String> {
    SiDoCode findSiDoCodeByAddr(String addr);
}
