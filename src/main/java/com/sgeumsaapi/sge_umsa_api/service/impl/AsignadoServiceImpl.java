package com.sgeumsaapi.sge_umsa_api.service.impl;

import com.sgeumsaapi.sge_umsa_api.DTO.asignacion.*;
import com.sgeumsaapi.sge_umsa_api.exception.ResourceNotFoundException;
import com.sgeumsaapi.sge_umsa_api.mapper.AsignadoMapper;
import com.sgeumsaapi.sge_umsa_api.model.Asignado;
import com.sgeumsaapi.sge_umsa_api.model.AsignadoId;
import com.sgeumsaapi.sge_umsa_api.repository.AsignadoRepository;
import com.sgeumsaapi.sge_umsa_api.service.AsignadoService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsignadoServiceImpl implements AsignadoService {

    private final AsignadoRepository asignadoRepository;
    private final AsignadoMapper asignadoMapper;

    public AsignadoServiceImpl(AsignadoRepository asignadoRepository, AsignadoMapper asignadoMapper) {
        this.asignadoRepository = asignadoRepository;
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
    public AsignadoDTO findById(Long idSalon, Long idEvento) {
        AsignadoId id = new AsignadoId(idSalon, idEvento);
        Asignado asignado = asignadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Asignación no encontrada para Salón ID: " + idSalon + " y Evento ID: " + idEvento));
        return asignadoMapper.toDto(asignado);
    }

    @Override
    public AsignadoDTO createAsignado(AsignadoCreateDTO asignadoCreateDTO) {
        Asignado asignado = asignadoMapper.toEntity(asignadoCreateDTO);
        Asignado savedAsignado = asignadoRepository.save(asignado);
        return asignadoMapper.toDto(savedAsignado);
    }

    @Override
    public AsignadoDTO updateAsignado(Long idSalon, Long idEvento, AsignadoCreateDTO asignadoCreateDTO) {
        AsignadoId id = new AsignadoId(idSalon, idEvento);
        Asignado asignado = asignadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Asignación no encontrada para Salón ID: " + idSalon + " y Evento ID: " + idEvento));
        
        asignadoMapper.updateFromDto(asignadoCreateDTO, asignado);
        Asignado updatedAsignado = asignadoRepository.save(asignado);
        return asignadoMapper.toDto(updatedAsignado);
    }

    @Override
    public void deleteAsignado(Long idSalon, Long idEvento) {
        AsignadoId id = new AsignadoId(idSalon, idEvento);
        if (!asignadoRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                "Asignación no encontrada para Salón ID: " + idSalon + " y Evento ID: " + idEvento);
        }
        asignadoRepository.deleteById(id);
    }

    @Override
    public List<AsignadoDTO> findByEventoId(Long idEvento) {
        return asignadoRepository.findByIdEvento(idEvento)
                .stream()
                .map(asignadoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AsignadoDTO> findBySalonId(Long idSalon) {
        return asignadoRepository.findByIdSalon(idSalon)
                .stream()
                .map(asignadoMapper::toDto)
                .collect(Collectors.toList());
    }
}