package com.sedikev.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AnimalDomain {

    private String id;
    private LoteDomain loteDomain;
    private String nombre;
    private BigDecimal peso;
    private String sexo;
    private Integer numLote;

    public boolean esMachote() {
        return "M".equals(sexo);
    }
}
