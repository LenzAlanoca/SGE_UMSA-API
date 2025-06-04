package com.sgeumsaapi.sge_umsa_api.repository;

import com.sgeumsaapi.sge_umsa_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    // Consultas personalizadas proximas
    boolean existsByCorreo(String correo);
    boolean existsByUsuario(String usuario);
}