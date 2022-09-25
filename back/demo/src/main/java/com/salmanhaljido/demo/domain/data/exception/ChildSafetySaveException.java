package com.salmanhaljido.demo.domain.data.exception;

public class ChildSafetySaveException extends RuntimeException{
    public ChildSafetySaveException() {
        super("어린이 보호구역 데이터셋 저장 실패");
    }
}
