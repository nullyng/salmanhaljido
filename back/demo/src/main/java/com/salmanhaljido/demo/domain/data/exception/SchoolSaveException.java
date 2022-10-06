package com.salmanhaljido.demo.domain.data.exception;

public class SchoolSaveException extends RuntimeException{
    public SchoolSaveException() {
        super("학교 데이터셋 저장 실패");
    }
}
