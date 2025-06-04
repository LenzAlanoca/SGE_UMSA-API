package com.sgeumsaapi.sge_umsa_api.controller;

import com.sgeumsaapi.sge_umsa_api.DTO.lugar.*;
import com.sgeumsaapi.sge_umsa_api.service.PredioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
