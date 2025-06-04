package com.sgeumsaapi.sge_umsa_api.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.sgeumsaapi.sge_umsa_api.DTO.lugar.SalonDTO;
import com.sgeumsaapi.sge_umsa_api.model.Salon;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SalonMapper {
    @Mapping(target = "predio", ignore = true)
    Salon toEntity(SalonDTO dto);
    
    @Mapping(source = "predio.idPredio", target = "idPredio")
    @Mapping(source = "predio.nombrePredio", target = "nombrePredio")
    SalonDTO toDto(Salon entity);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "predio", ignore = true)
    void updateFromDto(SalonDTO dto, @MappingTarget Salon entity);
}