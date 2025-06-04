package com.sgeumsaapi.sge_umsa_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgeumsaapi.sge_umsa_api.DTO.lugar.PredioDTO;
import com.sgeumsaapi.sge_umsa_api.service.PredioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/predios")
public class PredioController {

    private final PredioService predioService;

    public PredioController(PredioService predioService) {
        this.predioService = predioService;
    }

    @GetMapping
    public ResponseEntity<List<PredioDTO>> getAllPredios() {
        List<PredioDTO> predios = predioService.findAll();
        return ResponseEntity.ok(predios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PredioDTO> getPredioById(@PathVariable Long id) {
        PredioDTO predio = predioService.findById(id);
        return ResponseEntity.ok(predio);
    }

    @PostMapping
    public ResponseEntity<PredioDTO> createPredio(@Valid @RequestBody PredioDTO predioDTO) {
        PredioDTO nuevoPredio = predioService.createPredio(predioDTO);
        return new ResponseEntity<>(nuevoPredio, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PredioDTO> updatePredio(
            @PathVariable Long id,
            @Valid @RequestBody PredioDTO predioDTO) {
        PredioDTO predioActualizado = predioService.updatePredio(id, predioDTO);
        return ResponseEntity.ok(predioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePredio(@PathVariable Long id) {
        predioService.deletePredio(id);
        return ResponseEntity.noContent().build();
    }
}
