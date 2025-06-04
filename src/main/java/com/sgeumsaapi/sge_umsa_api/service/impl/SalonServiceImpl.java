package com.sgeumsaapi.sge_umsa_api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sgeumsaapi.sge_umsa_api.DTO.lugar.SalonDTO;
import com.sgeumsaapi.sge_umsa_api.exception.ResourceNotFoundException;
import com.sgeumsaapi.sge_umsa_api.mapper.SalonMapper;
import com.sgeumsaapi.sge_umsa_api.model.Predio;
import com.sgeumsaapi.sge_umsa_api.model.Salon;
import com.sgeumsaapi.sge_umsa_api.repository.PredioRepository;
import com.sgeumsaapi.sge_umsa_api.repository.SalonRepository;
import com.sgeumsaapi.sge_umsa_api.service.SalonService;

@Service
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;
    private final PredioRepository predioRepository;
    private final SalonMapper salonMapper;

    public SalonServiceImpl(SalonRepository salonRepository, 
                          PredioRepository predioRepository,
                          SalonMapper salonMapper) {
        this.salonRepository = salonRepository;
        this.predioRepository = predioRepository;
        this.salonMapper = salonMapper;
    }

    @Override
    public List<SalonDTO> findAll() {
        return salonRepository.findAll()
                .stream()
                .map(salonMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SalonDTO findById(Long id) {
        Salon salon = salonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Salon no encontrado con ID: " + id));
        return salonMapper.toDto(salon);
    }


    @Override
    public List<SalonDTO> findByPredio(Long predioId) {
        return salonRepository.findByPredio_IdPredio(predioId)
                .stream()
                .map(salonMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SalonDTO createSalon(SalonDTO salonDTO) {
        Predio predio = predioRepository.findById(salonDTO.getIdPredio())
                .orElseThrow(() -> new ResourceNotFoundException("Predio no encontrado"));
        
        Salon salon = salonMapper.toEntity(salonDTO);
        salon.setPredio(predio);
        
        Salon savedSalon = salonRepository.save(salon);
        return salonMapper.toDto(savedSalon);
    }

    @Override
    public SalonDTO updateSalon(Long id, SalonDTO salonDTO) {
        Salon salon = salonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Salon no encontrado con ID: " + id));
        
        Predio predio = predioRepository.findById(salonDTO.getIdPredio())
                .orElseThrow(() -> new ResourceNotFoundException("Predio no encontrado"));
        
        salonMapper.updateFromDto(salonDTO, salon);
        salon.setPredio(predio);
        
        Salon updatedSalon = salonRepository.save(salon);
        return salonMapper.toDto(updatedSalon);
    }

    @Override
    public void deleteSalon(Long id) {
        if (!salonRepository.existsById(id)) {
            throw new ResourceNotFoundException("Salon no encontrado con ID: " + id);
        }
        salonRepository.deleteById(id);
    }
}