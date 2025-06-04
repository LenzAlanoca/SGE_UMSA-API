package com.sgeumsaapi.sge_umsa_api.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.sgeumsaapi.sge_umsa_api.DTO.asignacion.AsignadoCreateDTO;
import com.sgeumsaapi.sge_umsa_api.DTO.asignacion.AsignadoDTO;
import com.sgeumsaapi.sge_umsa_api.model.Asignado;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AsignadoMapper {
    @Mapping(target = "evento", ignore = true)
    @Mapping(target = "salon", ignore = true)
    @Mapping(target = "id", ignore = true)
    Asignado toEntity(AsignadoCreateDTO dto);
    
    @Mapping(source = "salon.idSalon", target = "idSalon")
    @Mapping(source = "salon.nombreSalon", target = "nombreSalon")
    @Mapping(source = "evento.idEvento", target = "idEvento")
    @Mapping(source = "evento.titulo", target = "tituloEvento")
    AsignadoDTO toDto(Asignado entity);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "evento", ignore = true)
    @Mapping(target = "salon", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateFromDto(AsignadoCreateDTO dto, @MappingTarget Asignado entity);
}