package com.sedikev.application.usecase.animal;

import com.sedikev.application.dto.AnimalDTO;
import com.sedikev.application.usecase.UseCaseWithReturn;
import com.sedikev.domain.model.AnimalDomain;
import com.sedikev.domain.service.AnimalService;
import com.sedikev.application.mapper.AnimalMapper;
import com.sedikev.crosscutting.exception.custom.BusinessSedikevException;
import com.sedikev.crosscutting.helpers.TextHelper;
import java.math.BigDecimal;

public class CreateAnimalUseCase implements UseCaseWithReturn<AnimalDTO, AnimalDTO> {

    private final AnimalService animalService;
    private final AnimalMapper animalMapper;

    // Inyectamos las dependencias necesarias (AnimalService y AnimalMapper)
    public CreateAnimalUseCase(AnimalService animalService, AnimalMapper animalMapper) {
        this.animalService = animalService;
        this.animalMapper = animalMapper;
    }

    @Override
    public AnimalDTO ejecutar(AnimalDTO animalDTO) {
        // 1. Validar los datos de entrada
        validarDatos(animalDTO);

        // 2. Mapear el DTO a un Domain (lógica de negocio)
        AnimalDomain animalDomain = animalMapper.toDomain(animalDTO);

        // 3. Guardar el animal en el repositorio (a través del servicio)
        AnimalDomain savedAnimal = animalService.save(animalDomain);

        // 4. Mapear el Domain guardado de vuelta a un DTO
        return animalMapper.toDTO(savedAnimal);
    }

    // Método para validar los datos de entrada
    private void validarDatos(AnimalDTO animalDTO) {
        if (TextHelper.isEmptyApplyingTrim(animalDTO.getNombre())) {
            throw new BusinessSedikevException("El nombre del animal no puede estar vacío.");
        }

        if (animalDTO.getPeso() == null || animalDTO.getPeso().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessSedikevException("El peso del animal debe ser mayor que cero.");
        }

        if (animalDTO.getLote() == null) {
            throw new BusinessSedikevException("El animal debe estar asociado a un lote.");
        }
    }
}