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

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 100, message = "El título debe tener entre 3 y 100 caracteres")
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "^(ACTIVO|INACTIVO|CANCELADO|FINALIZADO)$",
            message = "El estado debe ser: ACTIVO, INACTIVO, CANCELADO o FINALIZADO")
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