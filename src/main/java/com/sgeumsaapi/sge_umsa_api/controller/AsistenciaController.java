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

import com.sgeumsaapi.sge_umsa_api.DTO.asistencia.AsistenciaCreateDTO;
import com.sgeumsaapi.sge_umsa_api.DTO.asistencia.AsistenciaDTO;
import com.sgeumsaapi.sge_umsa_api.service.AsistenciaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/asistencias")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    public AsistenciaController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    @GetMapping
    public ResponseEntity<List<AsistenciaDTO>> getAllAsistencias() {
        List<AsistenciaDTO> asistencias = asistenciaService.findAll();
        return ResponseEntity.ok(asistencias);
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<AsistenciaDTO>> getAsistenciasByEvento(@PathVariable Long eventoId) {
        List<AsistenciaDTO> asistencias = asistenciaService.findByEvento(eventoId);
        return ResponseEntity.ok(asistencias);
    }

    @GetMapping("/usuario/{ciUsuario}")
    public ResponseEntity<List<AsistenciaDTO>> getAsistenciasByUsuario(@PathVariable String ciUsuario) {
        List<AsistenciaDTO> asistencias = asistenciaService.findByUsuario(ciUsuario);
        return ResponseEntity.ok(asistencias);
    }

    @PostMapping
    public ResponseEntity<AsistenciaDTO> createAsistencia(
            @Valid @RequestBody AsistenciaCreateDTO asistenciaCreateDTO) {
        AsistenciaDTO nuevaAsistencia = asistenciaService.createAsistencia(asistenciaCreateDTO);
        return new ResponseEntity<>(nuevaAsistencia, HttpStatus.CREATED);
    }

    @PutMapping("/{eventoId}/{ciUsuario}")
    public ResponseEntity<AsistenciaDTO> updateAsistencia(
            @PathVariable Long eventoId,
            @PathVariable String ciUsuario,
            @Valid @RequestBody AsistenciaCreateDTO asistenciaCreateDTO) {
        AsistenciaDTO asistenciaActualizada = asistenciaService.updateAsistencia(
                eventoId, ciUsuario, asistenciaCreateDTO);
        return ResponseEntity.ok(asistenciaActualizada);
    }

    @DeleteMapping("/{eventoId}/{ciUsuario}")
    public ResponseEntity<Void> deleteAsistencia(
            @PathVariable Long eventoId,
            @PathVariable String ciUsuario) {
        asistenciaService.deleteAsistencia(eventoId, ciUsuario);
        return ResponseEntity.noContent().build();
    }
}