package com.sgeumsaapi.sge_umsa_api.service;

import com.sgeumsaapi.sge_umsa_api.DTO.asignacion.AsignadoCreateDTO;
import com.sgeumsaapi.sge_umsa_api.DTO.asignacion.AsignadoDTO;
import com.sgeumsaapi.sge_umsa_api.exception.ResourceNotFoundException;
import com.sgeumsaapi.sge_umsa_api.mapper.AsignadoMapper;
import com.sgeumsaapi.sge_umsa_api.model.Asignado;
import com.sgeumsaapi.sge_umsa_api.model.Evento;
import com.sgeumsaapi.sge_umsa_api.model.Salon;
import com.sgeumsaapi.sge_umsa_api.repository.AsignadoRepository;
import com.sgeumsaapi.sge_umsa_api.repository.EventoRepository;
import com.sgeumsaapi.sge_umsa_api.repository.SalonRepository;
import com.sgeumsaapi.sge_umsa_api.service.impl.AsignadoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AsignadoServiceImplTest {

    @Mock
    private AsignadoRepository asignadoRepository;
    @Mock
    private EventoRepository eventoRepository;
    @Mock
    private SalonRepository salonRepository;
    @Mock
    private AsignadoMapper asignadoMapper;

    @InjectMocks
    private AsignadoServiceImpl asignadoService;

    @Test
    void createAsignacion_shouldSuccessfullyCreateAssignment() {

        AsignadoCreateDTO createDTO = new AsignadoCreateDTO();
        createDTO.setIdEvento(1L);
        createDTO.setIdSalon(2L);

        Evento evento = new Evento();
        evento.setIdEvento(1L);

        Salon salon = new Salon();
        salon.setIdSalon(2L);

        Asignado asignado = new Asignado();
        asignado.setEvento(evento);
        asignado.setSalon(salon);
        asignado.setFechaAsignacion(LocalDateTime.now());

        AsignadoDTO expectedDTO = new AsignadoDTO();
        expectedDTO.setIdEvento(1L);
        expectedDTO.setIdSalon(2L);


        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));
        when(salonRepository.findById(2L)).thenReturn(Optional.of(salon));
        when(asignadoMapper.toEntity(createDTO)).thenReturn(asignado);
        when(asignadoRepository.save(asignado)).thenReturn(asignado);
        when(asignadoMapper.toDto(asignado)).thenReturn(expectedDTO);


        AsignadoDTO result = asignadoService.createAsignacion(createDTO);


        assertThat(result).isNotNull();
        assertThat(result.getIdEvento()).isEqualTo(1L);
        assertThat(result.getIdSalon()).isEqualTo(2L);
        verify(eventoRepository).findById(1L);
        verify(salonRepository).findById(2L);
        verify(asignadoRepository).save(asignado);
    }

    @Test
    void createAsignacion_whenEventoNotFound_shouldThrowException() {

        AsignadoCreateDTO createDTO = new AsignadoCreateDTO();
        createDTO.setIdEvento(99L); // ID no existente
        createDTO.setIdSalon(2L);

        when(eventoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> asignadoService.createAsignacion(createDTO))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Evento no encontrado");

        verify(eventoRepository).findById(99L);
        verify(salonRepository, never()).findById(any());
        verify(asignadoRepository, never()).save(any());
    }
}