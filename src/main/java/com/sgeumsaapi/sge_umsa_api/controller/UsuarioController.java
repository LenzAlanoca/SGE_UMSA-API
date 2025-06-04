package com.sgeumsaapi.sge_umsa_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgeumsaapi.sge_umsa_api.DTO.User.UsuarioCreateDTO;
import com.sgeumsaapi.sge_umsa_api.DTO.User.UsuarioDTO;
import com.sgeumsaapi.sge_umsa_api.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@Validated
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{ci}")
    public ResponseEntity<UsuarioDTO> getUsuarioByCi(@PathVariable String ci) {
        UsuarioDTO usuario = usuarioService.findByCi(ci);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        UsuarioDTO nuevoUsuario = usuarioService.createUsuario(usuarioCreateDTO);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{ci}")
    public ResponseEntity<UsuarioDTO> updateUsuario(
            @PathVariable String ci, 
            @Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        UsuarioDTO usuarioActualizado = usuarioService.updateUsuario(ci, usuarioCreateDTO);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/{ci}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String ci) {
        usuarioService.deleteUsuario(ci);
        return ResponseEntity.noContent().build();
    }
}