package com.salmanhaljido.demo.domain.code.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class SiDoCode {
    @Id
    private String code;

    private String addr;

    @OneToMany(mappedBy = "siDoCode", orphanRemoval = true, cascade = CascadeType.DETACH)
    private List<GuGunCode> guGunCodes = new ArrayList<>();

    private Double lat;

    private Double lng;

    @Builder
    public SiDoCode(String code, String addr){
        this.code = code;
        this.addr = addr;
    }

    public void updateLocation(Double lat, Double lng){
        this.lat = lat;
        this.lng = lng;
    }
}
