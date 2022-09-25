package com.salmanhaljido.demo.domain.data.exception;

public class LibrarySaveException extends RuntimeException{
    public LibrarySaveException() {
        super("도서관 데이터셋 저장 실패");
    }
}
