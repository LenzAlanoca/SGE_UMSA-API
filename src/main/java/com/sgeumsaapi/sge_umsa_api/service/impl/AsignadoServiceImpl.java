package com.sgeumsaapi.sge_umsa_api.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sgeumsaapi.sge_umsa_api.DTO.asignacion.AsignadoCreateDTO;
import com.sgeumsaapi.sge_umsa_api.DTO.asignacion.AsignadoDTO;
import com.sgeumsaapi.sge_umsa_api.exception.ResourceNotFoundException;
import com.sgeumsaapi.sge_umsa_api.mapper.AsignadoMapper;
import com.sgeumsaapi.sge_umsa_api.model.Asignado;
import com.sgeumsaapi.sge_umsa_api.model.AsignadoId;
import com.sgeumsaapi.sge_umsa_api.model.Evento;
import com.sgeumsaapi.sge_umsa_api.model.Salon;
import com.sgeumsaapi.sge_umsa_api.repository.AsignadoRepository;
import com.sgeumsaapi.sge_umsa_api.repository.EventoRepository;
import com.sgeumsaapi.sge_umsa_api.repository.SalonRepository;
import com.sgeumsaapi.sge_umsa_api.service.AsignadoService;

@Service
public class AsignadoServiceImpl implements AsignadoService {

    private final AsignadoRepository asignadoRepository;
    private final EventoRepository eventoRepository;
    private final SalonRepository salonRepository;
    private final AsignadoMapper asignadoMapper;

    public AsignadoServiceImpl(AsignadoRepository asignadoRepository,
                             EventoRepository eventoRepository,
                             SalonRepository salonRepository,
                             AsignadoMapper asignadoMapper) {
        this.asignadoRepository = asignadoRepository;
        this.eventoRepository = eventoRepository;
        this.salonRepository = salonRepository;
        this.asignadoMapper = asignadoMapper;
    }

    @Override
    public List<AsignadoDTO> findAll() {
        return asignadoRepository.findAll()
                .stream()
                .map(asignadoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AsignadoDTO> findByEvento(Long eventoId) {
        return asignadoRepository.findByIdIdEvento(eventoId)
                .stream()
                .map(asignadoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AsignadoDTO> findBySalon(Long salonId) {
        return asignadoRepository.findByIdIdSalon(salonId)
                .stream()
                .map(asignadoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AsignadoDTO createAsignacion(AsignadoCreateDTO asignadoCreateDTO) {
        Evento evento = eventoRepository.findById(asignadoCreateDTO.getIdEvento())
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado"));
        
        Salon salon = salonRepository.findById(asignadoCreateDTO.getIdSalon())
                .orElseThrow(() -> new ResourceNotFoundException("Salon no encontrado"));
        
        Asignado asignado = asignadoMapper.toEntity(asignadoCreateDTO);
        asignado.setEvento(evento);
        asignado.setSalon(salon);
        asignado.setFechaAsignacion(LocalDateTime.now());
        
        Asignado savedAsignado = asignadoRepository.save(asignado);
        return asignadoMapper.toDto(savedAsignado);
    }

    @Override
    public AsignadoDTO updateAsignacion(Long eventoId, Long salonId, AsignadoCreateDTO asignadoCreateDTO) {
        AsignadoId id = new AsignadoId();
        id.setIdEvento(eventoId);
        id.setIdSalon(salonId);
        
        Asignado asignado = asignadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asignación no encontrada"));
        
        asignadoMapper.updateFromDto(asignadoCreateDTO, asignado);
        Asignado updatedAsignado = asignadoRepository.save(asignado);
        return asignadoMapper.toDto(updatedAsignado);
    }

    @Override
    public void deleteAsignacion(Long eventoId, Long salonId) {
        AsignadoId id = new AsignadoId();
        id.setIdEvento(eventoId);
        id.setIdSalon(salonId);
        
        if (!asignadoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Asignación no encontrada");
        }
        asignadoRepository.deleteById(id);
    }
}