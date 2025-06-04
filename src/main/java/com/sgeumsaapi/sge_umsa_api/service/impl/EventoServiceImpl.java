package com.sgeumsaapi.sge_umsa_api.service.impl;

import com.sgeumsaapi.sge_umsa_api.DTO.evento.*;

import com.sgeumsaapi.sge_umsa_api.exception.ResourceNotFoundException;
import com.sgeumsaapi.sge_umsa_api.mapper.EventoMapper;
import com.sgeumsaapi.sge_umsa_api.model.Evento;
import com.sgeumsaapi.sge_umsa_api.model.Usuario;
import com.sgeumsaapi.sge_umsa_api.repository.EventoRepository;
import com.sgeumsaapi.sge_umsa_api.repository.UsuarioRepository;
import com.sgeumsaapi.sge_umsa_api.service.EventoService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EventoMapper eventoMapper;

    public EventoServiceImpl(EventoRepository eventoRepository, 
                           UsuarioRepository usuarioRepository,
                           EventoMapper eventoMapper) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
        this.eventoMapper = eventoMapper;
    }

    @Override
    public List<EventoDTO> findAll() {
        return eventoRepository.findAll()
                .stream()
                .map(eventoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventoDTO findById(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado con ID: " + id));
        return eventoMapper.toDto(evento);
    }

    @Override
    public List<EventoDTO> findByOrganizador(String ciOrganizador) {
        return eventoRepository.findByOrganizadorCi(ciOrganizador)
                .stream()
                .map(eventoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventoDTO createEvento(EventoCreateDTO eventoCreateDTO) {
        Usuario organizador = usuarioRepository.findById(eventoCreateDTO.getOrganizadorCi())
                .orElseThrow(() -> new ResourceNotFoundException("Organizador no encontrado"));
        
        Evento evento = eventoMapper.toEntity(eventoCreateDTO);
        evento.setOrganizador(organizador);
        evento.setEstado("PLANIFICADO"); // Estado por defecto
        
        Evento savedEvento = eventoRepository.save(evento);
        return eventoMapper.toDto(savedEvento);
    }

    @Override
    public EventoDTO updateEvento(Long id, EventoCreateDTO eventoCreateDTO) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado con ID: " + id));
        
        eventoMapper.updateFromDto(eventoCreateDTO, evento);
        Evento updatedEvento = eventoRepository.save(evento);
        return eventoMapper.toDto(updatedEvento);
    }

    @Override
    public EventoDTO changeEstado(Long id, String estado) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado con ID: " + id));
        
        evento.setEstado(estado);
        Evento updatedEvento = eventoRepository.save(evento);
        return eventoMapper.toDto(updatedEvento);
    }

    @Override
    public void deleteEvento(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Evento no encontrado con ID: " + id);
        }
        eventoRepository.deleteById(id);
    }
}