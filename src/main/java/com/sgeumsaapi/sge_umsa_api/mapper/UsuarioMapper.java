package com.sgeumsaapi.sge_umsa_api.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.sgeumsaapi.sge_umsa_api.DTO.User.UsuarioCreateDTO;
import com.sgeumsaapi.sge_umsa_api.DTO.User.UsuarioDTO;
import com.sgeumsaapi.sge_umsa_api.model.Usuario;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {
    Usuario toEntity(UsuarioCreateDTO dto);
    UsuarioDTO toDto(Usuario entity);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(UsuarioCreateDTO dto, @MappingTarget Usuario entity);
}