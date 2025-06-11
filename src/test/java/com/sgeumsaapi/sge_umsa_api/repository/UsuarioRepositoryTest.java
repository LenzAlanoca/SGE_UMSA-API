package com.sgeumsaapi.sge_umsa_api.repository;


import com.sgeumsaapi.sge_umsa_api.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UsuarioRepositoryIT {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Test
    void deberiaEncontrarUsuarioPorNombreUsuario() {
        // Given
        Usuario usuario = new Usuario();
        usuario.setCi("1234567");
        usuario.setNombre("María");
        usuario.setApellido("Gómez");
        usuario.setCorreo("maria.gomez@umsa.edu");
        usuario.setRol("ESTUDIANTE");
        usuario.setUsuario("mgomez");

        usuarioRepository.save(usuario);

        boolean existe = usuarioRepository.existsByUsuario("mgomez");
        assertThat(existe).isTrue();
    }

    @Test
    void noDeberiaEncontrarUsuarioPorCorreoInexistente() {
        boolean existe = usuarioRepository.existsByCorreo("correo.inexistente@umsa.edu");
        assertThat(existe).isFalse();
    }

    @Test
    void noDeberiaEncontrarUsuarioPorNombreUsuarioInexistente() {
        boolean existe = usuarioRepository.existsByUsuario("usuario_inexistente");

        assertThat(existe).isFalse();
    }

       
}