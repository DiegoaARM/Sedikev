package com.sedikev.application.mapper;

import com.sedikev.domain.model.LoteDomain;
import com.sedikev.application.dto.LoteDTO;
import com.sedikev.infrastructure.adapter.entity.LoteEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoteMapper {

    LoteMapper INSTANCE = Mappers.getMapper(LoteMapper.class);

    LoteDTO toDTO(LoteDomain loteDomain);
    LoteDomain toDomain(LoteDTO loteDTO);
    LoteEntity toEntity(LoteDomain loteDomain);
    LoteDomain toDomain(LoteEntity loteEntity);
}