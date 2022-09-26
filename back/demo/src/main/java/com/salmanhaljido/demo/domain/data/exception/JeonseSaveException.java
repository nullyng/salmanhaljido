package com.salmanhaljido.demo.domain.data.exception;

public class JeonseSaveException extends RuntimeException{
    public JeonseSaveException() {
        super("아파트 전세 가격 데이터셋 저장 실패");
    }
}
