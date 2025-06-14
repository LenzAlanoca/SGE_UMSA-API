package com.sgeumsaapi.sge_umsa_api.service;

import java.util.List;

import com.sgeumsaapi.sge_umsa_api.DTO.asistencia.AsistenciaCreateDTO;
import com.sgeumsaapi.sge_umsa_api.DTO.asistencia.AsistenciaDTO;

public interface AsistenciaService {
    List<AsistenciaDTO> findAll();
    List<AsistenciaDTO> findByEvento(Long eventoId);
    List<AsistenciaDTO> findByUsuario(String ciUsuario);
    AsistenciaDTO createAsistencia(AsistenciaCreateDTO asistenciaCreateDTO);
    AsistenciaDTO updateAsistencia(Long eventoId, String ciUsuario, AsistenciaCreateDTO asistenciaCreateDTO);
    void deleteAsistencia(Long eventoId, String ciUsuario);
}