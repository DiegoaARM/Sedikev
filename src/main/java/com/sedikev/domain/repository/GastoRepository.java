package com.sedikev.domain.repository;

import com.sedikev.infrastructure.adapter.entity.GastoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GastoRepository extends JpaRepository<GastoEntity, Long> {

    GastoEntity save(GastoEntity gastoEntity);

    Optional<GastoEntity> findById(Long id);

    void deleteById(Long id);

    List<GastoEntity> findAll();
}
