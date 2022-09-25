package com.salmanhaljido.demo.domain.data.exception;

public class FacilitiesForTheDisabledSaveException extends RuntimeException{
    public FacilitiesForTheDisabledSaveException() {
        super("장애인 편의시설 데이터셋 저장 실패");
    }
}
