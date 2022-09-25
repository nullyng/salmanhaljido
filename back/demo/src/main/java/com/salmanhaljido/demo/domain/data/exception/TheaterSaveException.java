package com.salmanhaljido.demo.domain.data.exception;

public class TheaterSaveException extends RuntimeException{
    public TheaterSaveException() {
        super("영화관 데이터셋 저장 실패");
    }
}
