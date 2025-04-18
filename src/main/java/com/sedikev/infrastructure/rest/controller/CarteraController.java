package com.sedikev.infrastructure.rest.controller;

import com.sedikev.application.dto.CarteraDTO;
import com.sedikev.application.mapper.CarteraMapper;
import com.sedikev.domain.model.CarteraDomain;
import com.sedikev.domain.service.CarteraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class CarteraController {

    @Autowired
    private CarteraService carteraService;

    @Autowired
    private CarteraMapper carteraMapper;

    @PostMapping(path = "cartera")
    public ResponseEntity<CarteraDTO> create(@Valid @RequestBody CarteraDTO carteraDTO) {
        CarteraDomain carteraDomain = carteraMapper.toDomain(carteraDTO);
        CarteraDomain carteraSaved = carteraService.save(carteraDomain);
        CarteraDTO responseDTO = carteraMapper.toDTO(carteraSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping(path = "cartera")
    public ResponseEntity<CarteraDTO> update(@Valid @RequestBody CarteraDTO carteraDTO) {
        CarteraDomain carteraDomain = carteraMapper.toDomain(carteraDTO);
        CarteraDomain carteraSaved = carteraService.save(carteraDomain);
        CarteraDTO responseDTO = carteraMapper.toDTO(carteraSaved);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping(path = "cartera/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carteraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "cartera/{id}")
    public ResponseEntity<CarteraDTO> findById(@PathVariable Long id) {
        CarteraDomain carteraDomain = carteraService.findById(id);
        if (carteraDomain == null) {
            return ResponseEntity.notFound().build();
        }
        CarteraDTO responseDTO = carteraMapper.toDTO(carteraDomain);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(path = "carteras")
    public ResponseEntity<List<CarteraDTO>> findAll() {
        List<CarteraDomain> carteraDomains = carteraService.findAll();
        List<CarteraDTO> responseDTOs = carteraDomains.stream()
                .map(carteraMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }
}