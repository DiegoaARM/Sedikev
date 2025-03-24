package com.sedikev.application.usecase.pago;

import com.sedikev.application.usecase.UseCaseWithReturn;
import com.sedikev.crosscutting.exception.custom.BusinessSedikevException;
import com.sedikev.domain.model.PagoDomain;
import com.sedikev.domain.repository.PagoRepository;
import com.sedikev.application.mapper.PagoMapper;
import com.sedikev.infrastructure.adapter.entity.PagoEntity;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@RequiredArgsConstructor
public class CreatePagoUseCase implements UseCaseWithReturn<PagoDomain, PagoDomain> {

    private final PagoRepository pagoRepository;
    private final PagoMapper pagoMapper;

    @Override
    public PagoDomain ejecutar(PagoDomain pagoDomain) {
        // Validación de negocio: Verificar que la cantidad no sea negativa
        if (pagoDomain.getCantidad().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessSedikevException("La cantidad no puede ser negativa");
        }
        if (pagoRepository.existsById(pagoDomain.getId())) {
            throw new BusinessSedikevException("El pago ya existe");
        }
        if (pagoDomain.getVenta() == null || pagoDomain.getVenta().getId() == null) {
            throw new BusinessSedikevException("El pago debe estar asociado a un venta");
        }
        if (pagoDomain.getUsuario() == null || pagoDomain.getUsuario().getId() == null) {
            throw new BusinessSedikevException("El pago debe estar asociado a un usuario");
        }
        if (!(Objects.equals(pagoDomain.getTipo_pago(), "venta") || Objects.equals(pagoDomain.getTipo_pago(), "abono")
                || Objects.equals(pagoDomain.getTipo_pago(), "credito"))) {
            throw new BusinessSedikevException("El tipo de pago debe ser venta, abono o credito");
        }
        if (pagoDomain.getCantidad() == null || pagoDomain.getCantidad().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessSedikevException("La cantidad debe ser un número positivo");
        }

        // Mapear y guardar el pago
        PagoEntity pagoEntity = pagoMapper.toEntity(pagoDomain);
        PagoEntity pagoSaved = pagoRepository.save(pagoEntity);
        return pagoMapper.toDomain(pagoSaved);
    }
}