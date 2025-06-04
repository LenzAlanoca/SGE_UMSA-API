package com.sgeumsaapi.sge_umsa_api.service.impl;

import com.sgeumsaapi.sge_umsa_api.DTO.lugar.PredioDTO;
import com.sgeumsaapi.sge_umsa_api.exception.ResourceNotFoundException;
import com.sgeumsaapi.sge_umsa_api.mapper.PredioMapper;
import com.sgeumsaapi.sge_umsa_api.model.Predio;
import com.sgeumsaapi.sge_umsa_api.repository.PredioRepository;
import com.sgeumsaapi.sge_umsa_api.service.PredioService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PredioServiceImpl implements PredioService {

    private final PredioRepository predioRepository;
    private final PredioMapper predioMapper;

    public PredioServiceImpl(PredioRepository predioRepository, PredioMapper predioMapper) {
        this.predioRepository = predioRepository;
        this.predioMapper = predioMapper;
    }

    @Override
    public List<PredioDTO> findAll() {
        return predioRepository.findAll()
                .stream()
                .map(predioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PredioDTO findById(Long idPredio) {
        Predio predio = predioRepository.findById(idPredio)
                .orElseThrow(() -> new ResourceNotFoundException("Predio no encontrado con ID: " + idPredio));
        return predioMapper.toDto(predio);
    }

    @Override
    public PredioDTO createPredio(PredioDTO predioDTO) {
        Predio predio = predioMapper.toEntity(predioDTO);
        // Asegurarse de no establecer ID para creación
        predio.setIdPredio(null);
        Predio savedPredio = predioRepository.save(predio);
        return predioMapper.toDto(savedPredio);
    }

    @Override
    public PredioDTO updatePredio(Long idPredio, PredioDTO predioDTO) {
        Predio predio = predioRepository.findById(idPredio)
                .orElseThrow(() -> new ResourceNotFoundException("Predio no encontrado con ID: " + idPredio));
        
        // Actualizar campos
        predio.setNombrePredio(predioDTO.getNombrePredio());
        predio.setUbicacion(predioDTO.getUbicacion());
        
        Predio updatedPredio = predioRepository.save(predio);
        return predioMapper.toDto(updatedPredio);
    }

    @Override
    public void deletePredio(Long idPredio) {
        if (!predioRepository.existsById(idPredio)) {
            throw new ResourceNotFoundException("Predio no encontrado con ID: " + idPredio);
        }
        predioRepository.deleteById(idPredio);
    }
}