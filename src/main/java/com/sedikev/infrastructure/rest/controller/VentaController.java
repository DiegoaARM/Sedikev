package com.sedikev.infrastructure.rest.controller;

import com.sedikev.application.dto.CarteraDTO;
import com.sedikev.application.dto.VentaDTO;
import com.sedikev.application.mapper.CarteraMapper;
import com.sedikev.application.mapper.VentaMapper;
import com.sedikev.domain.model.CarteraDomain;
import com.sedikev.domain.model.VentaDomain;
import com.sedikev.domain.service.CarteraService;
import com.sedikev.domain.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private VentaMapper ventaMapper;

    @PostMapping(path = "venta")
    public ResponseEntity<VentaDTO> create(@Valid @RequestBody VentaDTO ventaDTO) {
        VentaDomain ventaDomain = ventaMapper.toDomain(ventaDTO);
        VentaDomain ventaSaved = ventaService.save(ventaDomain);
        VentaDTO responseDTO = ventaMapper.toDTO(ventaSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping(path = "venta")
    public ResponseEntity<VentaDTO> update(@Valid @RequestBody VentaDTO ventaDTO) {
        VentaDomain ventaDomain = ventaMapper.toDomain(ventaDTO);
        VentaDomain ventaSaved = ventaService.save(ventaDomain);
        VentaDTO responseDTO = ventaMapper.toDTO(ventaSaved);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping(path = "venta/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ventaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "venta/{id}")
    public ResponseEntity<VentaDTO> findById(@PathVariable Long id) {
        VentaDomain ventaDomain = ventaService.findById(id);
        if (ventaDomain == null) {
            return ResponseEntity.notFound().build();
        }
        VentaDTO responseDTO = ventaMapper.toDTO(ventaDomain);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(path = "ventas")
    public ResponseEntity<List<VentaDTO>> findAll() {
        List<VentaDomain> ventaDomains = ventaService.findAll();
        List<VentaDTO> responseDTOs = ventaDomains.stream()
                .map(ventaMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }
}
