package com.salmanhaljido.demo.domain.code.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "sido_code")
    private SiDoCode siDoCode;

    @OneToMany(mappedBy = "guGunCode", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<DongCode> dongCodes = new ArrayList<>();

    public void setSiDoCode(SiDoCode siDoCode) {
        siDoCode.getGuGunCodes().add(this);
        this.siDoCode = siDoCode;
    }

    @Builder
    public GuGunCode(String code, String addr){
        this.code = code;
        this.addr = addr;
    }
}
