package com.sedikev.application.facade;

import com.sedikev.application.dto.AnimalDTO;
import com.sedikev.application.usecase.animal.CreateAnimalUseCase;

public class AnimalUseCaseFacade {

    private final CreateAnimalUseCase createAnimalUseCase;


    // Inyectamos todos los casos de uso relacionados con Animal
    public AnimalUseCaseFacade(
            CreateAnimalUseCase createAnimalUseCase) {
        this.createAnimalUseCase = createAnimalUseCase;

    }

    // Métodos para acceder a cada caso de uso
    public AnimalDTO createAnimal(AnimalDTO animalDTO) {
        return createAnimalUseCase.ejecutar(animalDTO);
    }

}