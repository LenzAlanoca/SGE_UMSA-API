package com.sgeumsaapi.sge_umsa_api.service;

import com.sgeumsaapi.sge_umsa_api.DTO.lugar.PredioDTO;
import java.util.List;

public interface PredioService {
    List<PredioDTO> findAll();
    PredioDTO findById(Long idPredio);
    PredioDTO createPredio(PredioDTO predioDTO);
    PredioDTO updatePredio(Long idPredio, PredioDTO predioDTO);
    void deletePredio(Long idPredio);
}