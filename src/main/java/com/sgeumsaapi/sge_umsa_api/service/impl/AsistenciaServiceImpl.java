package com.sgeumsaapi.sge_umsa_api.service.impl;

import com.sgeumsaapi.sge_umsa_api.DTO.asistencia.*;
import com.sgeumsaapi.sge_umsa_api.exception.ResourceNotFoundException;
import com.sgeumsaapi.sge_umsa_api.mapper.AsistenciaMapper;
import com.sgeumsaapi.sge_umsa_api.model.Asistencia;
import com.sgeumsaapi.sge_umsa_api.model.AsistenciaId;
import com.sgeumsaapi.sge_umsa_api.model.Evento;
import com.sgeumsaapi.sge_umsa_api.model.Usuario;
import com.sgeumsaapi.sge_umsa_api.repository.AsistenciaRepository;
import com.sgeumsaapi.sge_umsa_api.repository.EventoRepository;
import com.sgeumsaapi.sge_umsa_api.repository.UsuarioRepository;
import com.sgeumsaapi.sge_umsa_api.service.AsistenciaService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EventoRepository eventoRepository;
    private final AsistenciaMapper asistenciaMapper;

    public AsistenciaServiceImpl(AsistenciaRepository asistenciaRepository,
                               UsuarioRepository usuarioRepository,
                               EventoRepository eventoRepository,
                               AsistenciaMapper asistenciaMapper) {
        this.asistenciaRepository = asistenciaRepository;
        this.usuarioRepository = usuarioRepository;
        this.eventoRepository = eventoRepository;
        this.asistenciaMapper = asistenciaMapper;
    }

    @Override
    public List<AsistenciaDTO> findAll() {
        return asistenciaRepository.findAll()
                .stream()
                .map(asistenciaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AsistenciaDTO> findByEvento(Long eventoId) {
        return asistenciaRepository.findByIdIdEvento(eventoId)
                .stream()
                .map(asistenciaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AsistenciaDTO> findByUsuario(String ciUsuario) {
        return asistenciaRepository.findByIdCi(ciUsuario)
                .stream()
                .map(asistenciaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AsistenciaDTO createAsistencia(AsistenciaCreateDTO asistenciaCreateDTO) {
        Usuario usuario = usuarioRepository.findById(asistenciaCreateDTO.getCiUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        
        Evento evento = eventoRepository.findById(asistenciaCreateDTO.getIdEvento())
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado"));
        
        Asistencia asistencia = asistenciaMapper.toEntity(asistenciaCreateDTO);
        asistencia.setUsuario(usuario);
        asistencia.setEvento(evento);
        asistencia.setFecha(LocalDateTime.now());
        
        Asistencia savedAsistencia = asistenciaRepository.save(asistencia);
        return asistenciaMapper.toDto(savedAsistencia);
    }

    @Override
    public AsistenciaDTO updateAsistencia(Long eventoId, String ciUsuario, AsistenciaCreateDTO asistenciaCreateDTO) {
        AsistenciaId id = new AsistenciaId();
        id.setCi(ciUsuario);
        id.setIdEvento(eventoId);
        
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia no encontrada"));
        
        asistenciaMapper.updateFromDto(asistenciaCreateDTO, asistencia);
        Asistencia updatedAsistencia = asistenciaRepository.save(asistencia);
        return asistenciaMapper.toDto(updatedAsistencia);
    }

    @Override
    public void deleteAsistencia(Long eventoId, String ciUsuario) {
        AsistenciaId id = new AsistenciaId();
        id.setCi(ciUsuario);
        id.setIdEvento(eventoId);
        
        if (!asistenciaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Asistencia no encontrada");
        }
        asistenciaRepository.deleteById(id);
    }
}