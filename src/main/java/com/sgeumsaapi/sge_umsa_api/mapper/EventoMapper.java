package com.sgeumsaapi.sge_umsa_api.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.sgeumsaapi.sge_umsa_api.DTO.evento.EventoCreateDTO;
import com.sgeumsaapi.sge_umsa_api.DTO.evento.EventoDTO;
import com.sgeumsaapi.sge_umsa_api.model.Evento;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventoMapper {
    @Mapping(target = "organizador", ignore = true)
    @Mapping(target = "idEvento", ignore = true)
    Evento toEntity(EventoCreateDTO dto);
    
    @Mapping(source = "organizador.ci", target = "organizadorCi")
    @Mapping(source = "organizador.nombre", target = "organizadorNombre")
    EventoDTO toDto(Evento entity);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "organizador", ignore = true)
    void updateFromDto(EventoCreateDTO dto, @MappingTarget Evento entity);
}