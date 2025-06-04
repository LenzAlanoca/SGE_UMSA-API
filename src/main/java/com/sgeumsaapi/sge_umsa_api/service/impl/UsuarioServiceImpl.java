package com.sgeumsaapi.sge_umsa_api.service.impl;

import com.sgeumsaapi.sge_umsa_api.DTO.User.*;

import com.sgeumsaapi.sge_umsa_api.exception.ResourceNotFoundException;
import com.sgeumsaapi.sge_umsa_api.mapper.UsuarioMapper;
import com.sgeumsaapi.sge_umsa_api.model.Usuario;
import com.sgeumsaapi.sge_umsa_api.repository.UsuarioRepository;
import com.sgeumsaapi.sge_umsa_api.service.UsuarioService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO findByCi(String ci) {
        Usuario usuario = usuarioRepository.findById(ci)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con CI: " + ci));
        return usuarioMapper.toDto(usuario);
    }

    @Override
    public UsuarioDTO createUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioCreateDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(savedUsuario);
    }

    @Override
    public UsuarioDTO updateUsuario(String ci, UsuarioCreateDTO usuarioCreateDTO) {
        Usuario usuario = usuarioRepository.findById(ci)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con CI: " + ci));
        
        usuarioMapper.updateFromDto(usuarioCreateDTO, usuario);
        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(updatedUsuario);
    }

    @Override
    public void deleteUsuario(String ci) {
        if (!usuarioRepository.existsById(ci)) {
            throw new ResourceNotFoundException("Usuario no encontrado con CI: " + ci);
        }
        usuarioRepository.deleteById(ci);
    }
}