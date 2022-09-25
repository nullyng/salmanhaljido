package com.salmanhaljido.demo.domain.data.exception;

public class MartSaveException extends RuntimeException{
    public MartSaveException() {
        super("마트, 시장 데이터셋 저장 실패");
    }
}
