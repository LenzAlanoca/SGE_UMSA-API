package com.sgeumsaapi.sge_umsa_api.repository;

import com.sgeumsaapi.sge_umsa_api.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UsuarioRepositoryIT {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        usuarioRepository.deleteAll();
    }

    @Test
    void noDeberiaEncontrarUsuarioPorCorreoInexistente() {
        // When
        boolean existe = usuarioRepository.existsByCorreo("correo.inexistente@umsa.edu");

        // Then
        assertThat(existe).isFalse();
    }

    @Test
    void noDeberiaEncontrarUsuarioPorNombreUsuarioInexistente() {
        // When
        boolean existe = usuarioRepository.existsByUsuario("usuario_inexistente");

        // Then
        assertThat(existe).isFalse();
    }
}