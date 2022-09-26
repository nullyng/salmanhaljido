package com.salmanhaljido.demo.domain.data.exception;

public class KinderGardenSaveException extends RuntimeException{
    public KinderGardenSaveException() {
        super("어린이집 데이터셋 저장 실패");
    }
}
