package com.sedikev.application.usecase.gasto;

import com.sedikev.crosscutting.exception.custom.BusinessSedikevException;
import com.sedikev.domain.repository.GastoRepository;
import com.sedikev.application.usecase.UseCaseWithoutReturn;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteGastoUseCase implements UseCaseWithoutReturn<Long> {

    private final GastoRepository gastoRepository;

    @Override
    public void ejecutar(Long id) {
        // Validar que el gasto exista
        if (!gastoRepository.existsById(id)) {
            throw new BusinessSedikevException("El gasto no existe");
        }

        // Eliminar el gasto
        gastoRepository.deleteById(id);
    }
}
