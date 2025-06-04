package com.sgeumsaapi.sge_umsa_api.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.sgeumsaapi.sge_umsa_api.DTO.asistencia.AsistenciaCreateDTO;
import com.sgeumsaapi.sge_umsa_api.DTO.asistencia.AsistenciaDTO;
import com.sgeumsaapi.sge_umsa_api.model.Asistencia;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AsistenciaMapper {
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "evento", ignore = true)
    @Mapping(target = "id", ignore = true)
    Asistencia toEntity(AsistenciaCreateDTO dto);
    
    @Mapping(source = "usuario.ci", target = "ciUsuario")
    @Mapping(source = "usuario.nombre", target = "nombreUsuario")
    @Mapping(source = "evento.idEvento", target = "idEvento")
    @Mapping(source = "evento.titulo", target = "tituloEvento")
    AsistenciaDTO toDto(Asistencia entity);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "evento", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateFromDto(AsistenciaCreateDTO dto, @MappingTarget Asistencia entity);
}