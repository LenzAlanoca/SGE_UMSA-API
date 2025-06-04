package com.sgeumsaapi.sge_umsa_api.controller;

import com.sgeumsaapi.sge_umsa_api.DTO.User.*;

import com.sgeumsaapi.sge_umsa_api.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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