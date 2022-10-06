package com.salmanhaljido.demo.domain.data.exception;

public class TradingSaveException extends RuntimeException{
    public TradingSaveException() {
        super("아파트 매매 가격 데이터셋 저장 실패");
    }
}
