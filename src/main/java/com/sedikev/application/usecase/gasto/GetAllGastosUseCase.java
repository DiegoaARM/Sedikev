package com.sedikev.application.usecase.gasto;

import com.sedikev.application.usecase.UseCaseWithReturn;
import com.sedikev.domain.model.GastoDomain;
import com.sedikev.domain.repository.GastoRepository;
import com.sedikev.application.mapper.GastoMapper;
import com.sedikev.infrastructure.adapter.entity.GastoEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetAllGastosUseCase implements UseCaseWithReturn<Void, List<GastoDomain>> {

    private final GastoRepository gastoRepository;
    private final GastoMapper gastoMapper;

    @Override
    public List<GastoDomain> ejecutar(Void unused) {
        // Obtener todos los gastos
        return gastoRepository.findAll().stream()
                .map(gastoMapper::toDomain)
                .collect(Collectors.toList());
    }
}
