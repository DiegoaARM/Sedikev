package com.sedikev.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@Getter
@Setter
public class GastoDomain {

    private Long id;
    private LoteDomain lote;
    private UsuarioDomain usuario;
    private BigDecimal cantidad;
    private String descripcion;
    private LocalDate fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoteDomain getLote() {
        return lote;
    }

    public void setLote(LoteDomain lote) {
        this.lote = lote;
    }

    public UsuarioDomain getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDomain usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
