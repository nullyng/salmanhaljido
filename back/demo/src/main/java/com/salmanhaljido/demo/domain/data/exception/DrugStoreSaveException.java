package com.salmanhaljido.demo.domain.data.exception;

public class DrugStoreSaveException extends RuntimeException{
    public DrugStoreSaveException() {
        super("약국 데이터셋 저장 실패");
    }
}
