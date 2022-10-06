package com.salmanhaljido.demo.domain.data.exception;

public class ParkSaveException extends RuntimeException{
    public ParkSaveException() {
        super("공원 데이터셋 저장 실패");
    }
}
