package com.sedikev.application.usecase.usuario;

import com.sedikev.crosscutting.exception.custom.BusinessSedikevException;
import com.sedikev.domain.repository.UsuarioRepository;
import com.sedikev.application.usecase.UseCaseWithoutReturn;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteUsuarioUseCase implements UseCaseWithoutReturn<Long> {

    private final UsuarioRepository usuarioRepository;

    @Override
    public void ejecutar(Long id) {
        // Validar que el usuario exista
        if (!usuarioRepository.existsById(id)) {
            throw new BusinessSedikevException("El usuario no existe");
        }

        // Eliminar el usuario
        usuarioRepository.deleteById(id);
    }
}