package com.sgeumsaapi.sge_umsa_api.controller;

import com.sgeumsaapi.sge_umsa_api.DTO.User.UsuarioCreateDTO;
import com.sgeumsaapi.sge_umsa_api.DTO.User.UsuarioDTO;
import com.sgeumsaapi.sge_umsa_api.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void inicializar() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ Test 1: Obtener usuario por CI
    @Test
    void alObtenerUsuarioPorCi_deberiaRetornarUsuarioDTO() {
        // Arrange
        String ci = "123456";
        UsuarioDTO usuarioMock = new UsuarioDTO();
        usuarioMock.setCi(ci);
        usuarioMock.setNombre("Juan");

        when(usuarioService.findByCi(ci)).thenReturn(usuarioMock);

        // Act
        ResponseEntity<UsuarioDTO> respuesta = usuarioController.getUsuarioByCi(ci);

        // Assert
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("Juan", respuesta.getBody().getNombre());
        verify(usuarioService, times(1)).findByCi(ci);
    }

    // ✅ Test 2: Crear nuevo usuario
    @Test
    void alCrearUsuario_deberiaRetornarUsuarioCreadoConCodigo201() {
        // Arrange
        UsuarioCreateDTO nuevoUsuario = new UsuarioCreateDTO();
        nuevoUsuario.setCi("654321");
        nuevoUsuario.setNombre("Ana");

        UsuarioDTO usuarioCreado = new UsuarioDTO();
        usuarioCreado.setCi("654321");
        usuarioCreado.setNombre("Ana");

        when(usuarioService.createUsuario(nuevoUsuario)).thenReturn(usuarioCreado);

        // Act
        ResponseEntity<UsuarioDTO> respuesta = usuarioController.createUsuario(nuevoUsuario);

        // Assert
        assertEquals(201, respuesta.getStatusCodeValue());
        assertEquals("Ana", respuesta.getBody().getNombre());
        verify(usuarioService, times(1)).createUsuario(nuevoUsuario);
    }
}
