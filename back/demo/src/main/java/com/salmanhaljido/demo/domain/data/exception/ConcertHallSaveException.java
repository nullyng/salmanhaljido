package com.salmanhaljido.demo.domain.data.exception;

public class ConcertHallSaveException extends RuntimeException{
    public ConcertHallSaveException() {
        super("공연장 데이터셋 저장 실패");
    }
}
