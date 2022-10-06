package com.salmanhaljido.demo.domain.data.exception;

public class AnimalHospitalSaveException extends RuntimeException{
    public AnimalHospitalSaveException() {
        super("동물병원 데이터셋 저장 실패");
    }
}
