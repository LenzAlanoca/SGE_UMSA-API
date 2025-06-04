package com.sgeumsaapi.sge_umsa_api.service;

import java.util.List;

import com.sgeumsaapi.sge_umsa_api.DTO.lugar.PredioDTO;

public interface PredioService {
    List<PredioDTO> findAll();
    PredioDTO findById(Long id);
    PredioDTO createPredio(PredioDTO predioDTO);
    PredioDTO updatePredio(Long id, PredioDTO predioDTO);
    void deletePredio(Long id);
}