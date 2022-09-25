package com.salmanhaljido.demo.domain.data.exception;

public class CarAccidentSaveException extends RuntimeException{
    public CarAccidentSaveException() {
        super("교통사고 데이터셋 저장 실패");
    }
}
