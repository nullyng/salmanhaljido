package com.salmanhaljido.demo.domain.data.exception;

public class EntertainmentSaveException extends RuntimeException{
    public EntertainmentSaveException() {
        super("엔터테인먼트 데이터셋 저장 실패");
    }
}
