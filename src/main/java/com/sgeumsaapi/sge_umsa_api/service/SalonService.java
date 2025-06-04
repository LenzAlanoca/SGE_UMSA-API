package com.sgeumsaapi.sge_umsa_api.service;

import java.util.List;

import com.sgeumsaapi.sge_umsa_api.DTO.lugar.SalonDTO;

public interface SalonService {
    List<SalonDTO> findAll();
    SalonDTO findById(Long id);
    List<SalonDTO> findByPredio(Long predioId);
    SalonDTO createSalon(SalonDTO salonDTO);
    SalonDTO updateSalon(Long id, SalonDTO salonDTO);
    void deleteSalon(Long id);
}