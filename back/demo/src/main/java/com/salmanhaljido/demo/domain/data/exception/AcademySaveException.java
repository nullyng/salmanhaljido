package com.salmanhaljido.demo.domain.data.exception;

public class AcademySaveException extends RuntimeException{
    public AcademySaveException() {
        super("학원 데이터셋 저장 실패");
    }
}
