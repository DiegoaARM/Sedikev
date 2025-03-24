package com.sedikev.application.service;

import com.sedikev.application.usecase.venta.*;
import com.sedikev.domain.model.VentaDomain;
import com.sedikev.domain.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final CreateVentaUseCase createVentaUseCase;
    private final UpdateVentaUseCase updateVentaUseCase;
    private final DeleteVentaUseCase deleteVentaUseCase;
    private final GetVentaByIdUseCase getVentaByIdUseCase;
    private final GetAllVentasUseCase getAllVentasUseCase;

    @Override
    public VentaDomain save(VentaDomain ventaDomain) {
        return createVentaUseCase.ejecutar(ventaDomain);
    }

    @Override
    public VentaDomain update(VentaDomain ventaDomain) {
        return updateVentaUseCase.ejecutar(ventaDomain);
    }

    @Override
    public VentaDomain findById(Long id) {
        return getVentaByIdUseCase.ejecutar(id);
    }

    @Override
    public void deleteById(Long id) {
        deleteVentaUseCase.ejecutar(id);
    }

    @Override
    public List<VentaDomain> findAll() {
        return getAllVentasUseCase.ejecutar(null);
    }
}
