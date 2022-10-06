package com.salmanhaljido.demo.domain.data.exception;

public class HospitalSaveException extends RuntimeException{
    public HospitalSaveException() {
        super("병원 데이터셋 저장 실패");
    }
}
