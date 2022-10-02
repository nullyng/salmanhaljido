package com.salmanhaljido.demo.domain.academy.service;

import com.salmanhaljido.demo.domain.academy.entity.AcademyDoc;
import com.salmanhaljido.demo.domain.code.entity.GuGunCode;
import com.salmanhaljido.demo.domain.code.entity.SiDoCode;

import java.util.List;

public interface AcademyService {
    void getData() throws Exception;
    List<AcademyDoc> findAcademyDocsBySd(SiDoCode siDoCode);
    List<AcademyDoc> findAcademyDocsBySgg(GuGunCode guGunCode);
}
