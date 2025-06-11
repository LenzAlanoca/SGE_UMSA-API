package com.sgeumsaapi.sge_umsa_api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "EVENTO")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long idEvento;

    // Validaciones mejoradas para título
    @NotBlank(message = "El título es obligatorio y no puede estar vacío")
    @Size(min = 5, max = 100, message = "El título debe tener entre 5 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\\s\\-.,;:()]+$",
            message = "El título solo puede contener letras, números, espacios y signos de puntuación básicos")
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    // Validaciones mejoradas para descripción
    @Size(min = 10, max = 1000, message = "La descripción debe tener entre 10 y 1000 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\\s\\-.,;:()\n\r]+$",
            message = "La descripción contiene caracteres no válidos")
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    // Validaciones mejoradas para estado
    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "^(ACTIVO|INACTIVO|CANCELADO|FINALIZADO|PROGRAMADO)$",
            message = "El estado debe ser uno de los siguientes: ACTIVO, INACTIVO, CANCELADO, FINALIZADO o PROGRAMADO")
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @NotBlank(message = "El tipo de evento es obligatorio")
    @Pattern(regexp = "^(ACADEMICO|CULTURAL|DEPORTIVO|SOCIAL|INSTITUCIONAL)$",
            message = "El tipo de evento debe ser: ACADEMICO, CULTURAL, DEPORTIVO, SOCIAL o INSTITUCIONAL")
    @Column(name = "tipo_evento", nullable = false, length = 30)
    private String tipoEvento;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @Future(message = "La fecha de inicio debe ser posterior a la fecha actual")
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @NotNull(message = "La fecha final es obligatoria")
    @Column(name = "fecha_final", nullable = false)
    private LocalDateTime fechaFinal;

    @NotNull(message = "El organizador es obligatorio")
    @ManyToOne
    @JoinColumn(name = "ci_organizador", referencedColumnName = "ci", nullable = false)
    private Usuario organizador;

    // Validación personalizada para fechas
    @jakarta.validation.constraints.AssertTrue(message = "La fecha final debe ser posterior a la fecha de inicio")
    public boolean isFechaFinalValida() {
        if (fechaInicio == null || fechaFinal == null) {
            return true; // Se validará por @NotNull
        }
        return fechaFinal.isAfter(fechaInicio);
    }

    // Validación personalizada para el título (sin palabras ofensivas básicas)
    @jakarta.validation.constraints.AssertTrue(message = "El título contiene palabras no apropiadas")
    public boolean isTituloApropiado() {
        if (titulo == null || titulo.trim().isEmpty()) {
            return true; // Se validará por @NotBlank
        }
        String tituloLower = titulo.toLowerCase().trim();
        String[] palabrasProhibidas = {"test", "prueba", "xxx", "temporal"};

        for (String palabra : palabrasProhibidas) {
            if (tituloLower.contains(palabra)) {
                return false;
            }
        }
        return true;
    }

    // Validación personalizada para la descripción
    @jakarta.validation.constraints.AssertTrue(message = "La descripción debe tener contenido significativo")
    public boolean isDescripcionSignificativa() {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            return true; // Descripción opcional, pero si existe debe ser significativa
        }

        String desc = descripcion.trim();
        // Validar que no sea solo espacios o caracteres repetidos
        if (desc.length() < 10) {
            return false;
        }

        // Validar que no sean solo caracteres repetidos (ej: "aaaaaaaaaa")
        if (desc.chars().distinct().count() < 3) {
            return false;
        }

        return true;
    }

    // Getters y Setters
    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDateTime fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Usuario getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Usuario organizador) {
        this.organizador = organizador;
    }
}