package com.sgeumsaapi.sge_umsa_api.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EventoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String estado;
}
