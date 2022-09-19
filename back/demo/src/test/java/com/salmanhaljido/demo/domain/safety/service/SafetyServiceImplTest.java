package com.salmanhaljido.demo.domain.safety.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ExtendWith(MockitoExtension.class)
public class SafetyServiceImplTest {
    private Logger logger = LoggerFactory.getLogger(SafetyServiceImplTest.class);

    @Spy
    @InjectMocks
    private SafetyServiceImpl safetyServiceImpl;


    @DisplayName("[Safety][CarAccident] 교통 사고 지역 호출 테스트")
    @Test
    void givenNothing_whenRequestingCarAccident_thenReturn(){
        // given

        // when
        safetyServiceImpl.getCarAccident();

        // then

    }

}

