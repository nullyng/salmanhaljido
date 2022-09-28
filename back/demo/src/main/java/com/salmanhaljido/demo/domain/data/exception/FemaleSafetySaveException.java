package com.salmanhaljido.demo.domain.data.exception;

public class FemaleSafetySaveException extends RuntimeException{
    public FemaleSafetySaveException() {
        super("여성안심지킴이집 데이터셋 저장 실패");
    }
}
