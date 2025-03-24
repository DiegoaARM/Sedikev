package com.sedikev.application.usecase.venta;

import com.sedikev.application.usecase.UseCaseWithReturn;
import com.sedikev.crosscutting.exception.custom.BusinessSedikevException;
import com.sedikev.domain.model.VentaDomain;
import com.sedikev.domain.repository.VentaRepository;
import com.sedikev.application.mapper.VentaMapper;
import com.sedikev.infrastructure.adapter.entity.VentaEntity;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class CreateVentaUseCase implements UseCaseWithReturn<VentaDomain, VentaDomain> {

    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;

    @Override
    public VentaDomain ejecutar(VentaDomain ventaDomain) {
        // Validación de negocio: Verificar si la venta ya existe en la base de datos
        if (ventaDomain.getId() != null && ventaRepository.existsById(ventaDomain.getId())) {
            throw new BusinessSedikevException("La venta ya existe en la base de datos");
        }

        // Validación de negocio: Verificar que la venta tenga un animal relacionado
        if (ventaDomain.getAnimal() == null || ventaDomain.getAnimal().getId() == null) {
            throw new BusinessSedikevException("La venta debe tener un animal relacionado");
        }

        // Validación de negocio: Verificar que la venta tenga un usuario relacionado
        if (ventaDomain.getUsuario() == null || ventaDomain.getUsuario().getId() == null) {
            throw new BusinessSedikevException("La venta debe tener un usuario relacionado");
        }

        // Validación de negocio: Verificar que el estado sea "pagado" o "no pagado"
        if (!ventaDomain.getEstado().equalsIgnoreCase("pagado") &&
                !ventaDomain.getEstado().equalsIgnoreCase("no pagado")) {
            throw new BusinessSedikevException("El estado debe ser 'pagado' o 'no pagado'");
        }

        // Validación de negocio: Verificar que el precio por kilo sea mayor que 0
        if (ventaDomain.getPrecio_kilo().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessSedikevException("El precio por kilo debe ser mayor que 0");
        }

        // Mapear y guardar la venta
        VentaEntity ventaEntity = ventaMapper.toEntity(ventaDomain);
        VentaEntity ventaSaved = ventaRepository.save(ventaEntity);
        return ventaMapper.toDomain(ventaSaved);
    }
}