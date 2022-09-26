package com.salmanhaljido.demo.domain.data.exception;

public class CrimeSaveException extends RuntimeException{
    public CrimeSaveException() {
        super("범죄 데이터셋 저장 실패");
    }
}
