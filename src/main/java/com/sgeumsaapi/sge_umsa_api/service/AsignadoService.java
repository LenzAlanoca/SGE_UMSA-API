package com.sgeumsaapi.sge_umsa_api.service;

import com.sgeumsaapi.sge_umsa_api.DTO.asignacion.*;

import java.util.List;

public interface AsignadoService {
    List<AsignadoDTO> findAll();
    AsignadoDTO findById(Long idSalon, Long idEvento);
    AsignadoDTO createAsignado(AsignadoCreateDTO asignadoCreateDTO);
    AsignadoDTO updateAsignado(Long idSalon, Long idEvento, AsignadoCreateDTO asignadoCreateDTO);
    void deleteAsignado(Long idSalon, Long idEvento);
    List<AsignadoDTO> findByEventoId(Long idEvento);
    List<AsignadoDTO> findBySalonId(Long idSalon);
}