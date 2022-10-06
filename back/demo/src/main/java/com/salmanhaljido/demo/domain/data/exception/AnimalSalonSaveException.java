package com.salmanhaljido.demo.domain.data.exception;

public class AnimalSalonSaveException extends RuntimeException{
    public AnimalSalonSaveException() {
        super("동물미용원 데이터셋 저장 실패");
    }
}
