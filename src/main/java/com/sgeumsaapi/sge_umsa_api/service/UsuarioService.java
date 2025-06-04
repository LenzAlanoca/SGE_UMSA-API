package com.sgeumsaapi.sge_umsa_api.service;

import com.sgeumsaapi.sge_umsa_api.DTO.User.*;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> findAll();
    UsuarioDTO findByCi(String ci);
    UsuarioDTO createUsuario(UsuarioCreateDTO usuarioCreateDTO);
    UsuarioDTO updateUsuario(String ci, UsuarioCreateDTO usuarioCreateDTO);
    void deleteUsuario(String ci);
}