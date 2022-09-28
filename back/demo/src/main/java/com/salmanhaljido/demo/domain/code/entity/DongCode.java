package com.salmanhaljido.demo.domain.code.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class DongCode {
    @Id
    private String code;

    private String addr;

    @Builder
    public DongCode(String code, String addr){
        this.code = code;
        this.addr = addr;
    }
}
