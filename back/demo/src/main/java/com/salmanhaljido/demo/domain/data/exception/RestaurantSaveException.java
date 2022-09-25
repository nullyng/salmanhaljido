package com.salmanhaljido.demo.domain.data.exception;

public class RestaurantSaveException extends RuntimeException{
    public RestaurantSaveException() {
        super("식당 데이터셋 저장 실패");
    }
}
