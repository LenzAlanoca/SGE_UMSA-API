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

import com.sgeumsaapi.sge_umsa_api.DTO.lugar.SalonDTO;
import com.sgeumsaapi.sge_umsa_api.service.SalonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/salones")
public class SalonController {

    private final SalonService salonService;

    public SalonController(SalonService salonService) {
        this.salonService = salonService;
    }

    @GetMapping
    public ResponseEntity<List<SalonDTO>> getAllSalones() {
        List<SalonDTO> salones = salonService.findAll();
        return ResponseEntity.ok(salones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalonDTO> getSalonById(@PathVariable Long id) {
        SalonDTO salon = salonService.findById(id);
        return ResponseEntity.ok(salon);
    }

    @GetMapping("/predio/{predioId}")
    public ResponseEntity<List<SalonDTO>> getSalonesByPredio(@PathVariable Long predioId) {
        List<SalonDTO> salones = salonService.findByPredio(predioId);
        return ResponseEntity.ok(salones);
    }

    @PostMapping
    public ResponseEntity<SalonDTO> createSalon(@Valid @RequestBody SalonDTO salonDTO) {
        SalonDTO nuevoSalon = salonService.createSalon(salonDTO);
        return new ResponseEntity<>(nuevoSalon, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalonDTO> updateSalon(
            @PathVariable Long id,
            @Valid @RequestBody SalonDTO salonDTO) {
        SalonDTO salonActualizado = salonService.updateSalon(id, salonDTO);
        return ResponseEntity.ok(salonActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalon(@PathVariable Long id) {
        salonService.deleteSalon(id);
        return ResponseEntity.noContent().build();
    }
}