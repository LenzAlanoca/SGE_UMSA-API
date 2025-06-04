package com.sgeumsaapi.sge_umsa_api.service;

import java.util.List;

import com.sgeumsaapi.sge_umsa_api.DTO.asignacion.AsignadoCreateDTO;
import com.sgeumsaapi.sge_umsa_api.DTO.asignacion.AsignadoDTO;

public interface AsignadoService {
    List<AsignadoDTO> findAll();
    List<AsignadoDTO> findByEvento(Long eventoId);
    List<AsignadoDTO> findBySalon(Long salonId);
    AsignadoDTO createAsignacion(AsignadoCreateDTO asignadoCreateDTO);
    AsignadoDTO updateAsignacion(Long eventoId, Long salonId, AsignadoCreateDTO asignadoCreateDTO);
    void deleteAsignacion(Long eventoId, Long salonId);
}