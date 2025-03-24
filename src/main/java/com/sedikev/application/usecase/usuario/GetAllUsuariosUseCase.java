package com.sedikev.application.usecase.usuario;

import com.sedikev.application.usecase.UseCaseWithReturn;
import com.sedikev.domain.model.UsuarioDomain;
import com.sedikev.domain.repository.UsuarioRepository;
import com.sedikev.application.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetAllUsuariosUseCase implements UseCaseWithReturn<Void, List<UsuarioDomain>> {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public List<UsuarioDomain> ejecutar(Void unused) {
        // Obtener todos los usuarios
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDomain)
                .collect(Collectors.toList());
    }
}
