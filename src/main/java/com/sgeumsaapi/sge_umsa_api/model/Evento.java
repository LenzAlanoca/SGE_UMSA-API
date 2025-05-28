package com.sistemaGestionEventos.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_evento;
    private String titulo;
    private String descripcion;
    private LocalDateTime fecha_inicio;
    private LocalDateTime fecha_final;
    private String tipo;
    private String estado;
    private String organizador;
}
