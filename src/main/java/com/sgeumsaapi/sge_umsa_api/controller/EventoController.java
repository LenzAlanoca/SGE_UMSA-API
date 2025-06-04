package com.sgeumsaapi.sge_umsa_api.controller;

import com.sgeumsaapi.sge_umsa_api.DTO.evento.*;

import com.sgeumsaapi.sge_umsa_api.service.EventoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public ResponseEntity<List<EventoDTO>> getAllEventos() {
        List<EventoDTO> eventos = eventoService.findAll();
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> getEventoById(@PathVariable Long id) {
        EventoDTO evento = eventoService.findById(id);
        return ResponseEntity.ok(evento);
    }

    @GetMapping("/organizador/{ciOrganizador}")
    public ResponseEntity<List<EventoDTO>> getEventosByOrganizador(@PathVariable String ciOrganizador) {
        List<EventoDTO> eventos = eventoService.findByOrganizador(ciOrganizador);
        return ResponseEntity.ok(eventos);
    }

    @PostMapping
    public ResponseEntity<EventoDTO> createEvento(@Valid @RequestBody EventoCreateDTO eventoCreateDTO) {
        EventoDTO nuevoEvento = eventoService.createEvento(eventoCreateDTO);
        return new ResponseEntity<>(nuevoEvento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> updateEvento(
            @PathVariable Long id,
            @Valid @RequestBody EventoCreateDTO eventoCreateDTO) {
        EventoDTO eventoActualizado = eventoService.updateEvento(id, eventoCreateDTO);
        return ResponseEntity.ok(eventoActualizado);
    }

    @PutMapping("/{id}/estado/{estado}")
    public ResponseEntity<EventoDTO> changeEstado(
            @PathVariable Long id,
            @PathVariable String estado) {
        EventoDTO evento = eventoService.changeEstado(id, estado);
        return ResponseEntity.ok(evento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }
}