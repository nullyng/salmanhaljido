package com.salmanhaljido.demo.domain.data.exception;

public class SportsFacilitiesSaveException extends RuntimeException{
    public SportsFacilitiesSaveException() {
        super("스포츠 시설 데이터셋 저장 실패");
    }
}
