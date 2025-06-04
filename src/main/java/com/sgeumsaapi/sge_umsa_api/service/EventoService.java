package com.sgeumsaapi.sge_umsa_api.service;

import com.sgeumsaapi.sge_umsa_api.DTO.evento.*;
import java.util.List;

public interface EventoService {
    List<EventoDTO> findAll();
    EventoDTO findById(Long id);
    List<EventoDTO> findByOrganizador(String ciOrganizador);
    EventoDTO createEvento(EventoCreateDTO eventoCreateDTO);
    EventoDTO updateEvento(Long id, EventoCreateDTO eventoCreateDTO);
    EventoDTO changeEstado(Long id, String estado);
    void deleteEvento(Long id);
}