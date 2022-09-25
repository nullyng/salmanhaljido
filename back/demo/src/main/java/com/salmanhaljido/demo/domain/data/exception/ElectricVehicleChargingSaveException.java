package com.salmanhaljido.demo.domain.data.exception;

public class ElectricVehicleChargingSaveException extends RuntimeException{
    public ElectricVehicleChargingSaveException() {
        super("전기차 충전소 데이터셋 저장 실패");
    }
}
