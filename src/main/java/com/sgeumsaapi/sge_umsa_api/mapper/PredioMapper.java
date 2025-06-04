package com.sgeumsaapi.sge_umsa_api.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.sgeumsaapi.sge_umsa_api.DTO.lugar.PredioDTO;
import com.sgeumsaapi.sge_umsa_api.model.Predio;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PredioMapper {
    Predio toEntity(PredioDTO dto);
    PredioDTO toDto(Predio entity);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(PredioDTO dto, @MappingTarget Predio entity);
}