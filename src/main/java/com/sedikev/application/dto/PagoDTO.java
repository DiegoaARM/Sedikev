package com.sedikev.application.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {

    private Long id;
    private VentaDTO venta;
    private UsuarioDTO usuario;
    private String tipoPago;
    private BigDecimal cantidad;
    private String descripcion;
    private LocalDate fecha;
}
