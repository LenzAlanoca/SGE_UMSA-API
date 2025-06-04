package com.sgeumsaapi.sge_umsa_api.controller;

import com.sgeumsaapi.sge_umsa_api.DTO.asignacion.*;
import com.sgeumsaapi.sge_umsa_api.service.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/asignaciones")
public class AsignadoController {

    private final AsignadoService asignadoService;

    public AsignadoController(AsignadoService asignadoService) {
        this.asignadoService = asignadoService;
    }

    @GetMapping
    public ResponseEntity<List<AsignadoDTO>> getAllAsignaciones() {
        List<AsignadoDTO> asignaciones = asignadoService.findAll();
        return ResponseEntity.ok(asignaciones);
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<AsignadoDTO>> getAsignacionesByEvento(@PathVariable Long eventoId) {
        List<AsignadoDTO> asignaciones = asignadoService.findByEvento(eventoId);
        return ResponseEntity.ok(asignaciones);
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<AsignadoDTO>> getAsignacionesBySalon(@PathVariable Long salonId) {
        List<AsignadoDTO> asignaciones = asignadoService.findBySalon(salonId);
        return ResponseEntity.ok(asignaciones);
    }

    @PostMapping
    public ResponseEntity<AsignadoDTO> createAsignacion(
            @Valid @RequestBody AsignadoCreateDTO asignadoCreateDTO) {
        AsignadoDTO nuevaAsignacion = asignadoService.createAsignacion(asignadoCreateDTO);
        return new ResponseEntity<>(nuevaAsignacion, HttpStatus.CREATED);
    }

    @PutMapping("/{eventoId}/{salonId}")
    public ResponseEntity<AsignadoDTO> updateAsignacion(
            @PathVariable Long eventoId,
            @PathVariable Long salonId,
            @Valid @RequestBody AsignadoCreateDTO asignadoCreateDTO) {
        AsignadoDTO asignacionActualizada = asignadoService.updateAsignacion(
                eventoId, salonId, asignadoCreateDTO);
        return ResponseEntity.ok(asignacionActualizada);
    }

    @DeleteMapping("/{eventoId}/{salonId}")
    public ResponseEntity<Void> deleteAsignacion(
            @PathVariable Long eventoId,
            @PathVariable Long salonId) {
        asignadoService.deleteAsignacion(eventoId, salonId);
        return ResponseEntity.noContent().build();
    }
}