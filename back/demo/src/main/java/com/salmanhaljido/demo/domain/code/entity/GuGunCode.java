package com.salmanhaljido.demo.domain.code.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class GuGunCode {
    @Id
    private String code;

    private String addr;

    private Double lat;

    private Double lng;

    @ManyToOne
    @JoinColumn(name = "sido_code")
    private SiDoCode siDoCode;

    public void setSiDoCode(SiDoCode siDoCode) {
        siDoCode.getGuGunCodes().add(this);
        this.siDoCode = siDoCode;
    }

    @Builder
    public GuGunCode(String code, String addr){
        this.code = code;
        this.addr = addr;
    }

    public void updateLocation(Double lat, Double lng){
        this.lat = lat;
        this.lng = lng;
    }
}
