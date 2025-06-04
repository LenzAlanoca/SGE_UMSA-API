package com.sgeumsaapi.sge_umsa_api.service;

import com.sgeumsaapi.sge_umsa_api.DTO.lugar.SalonDTO;
import java.util.List;

public interface SalonService {
    List<SalonDTO> findAll();
    SalonDTO findById(Long idSalon);
    SalonDTO createSalon(SalonDTO salonDTO);
    SalonDTO updateSalon(Long idSalon, SalonDTO salonDTO);
    void deleteSalon(Long idSalon);
    List<SalonDTO> findByPredioId(Long idPredio);
}