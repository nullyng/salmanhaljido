package com.salmanhaljido.demo.domain.data.exception;

public class ShelterSaveException extends RuntimeException{
    public ShelterSaveException() {
        super("대피소 데이터셋 저장 실패");
    }
}
