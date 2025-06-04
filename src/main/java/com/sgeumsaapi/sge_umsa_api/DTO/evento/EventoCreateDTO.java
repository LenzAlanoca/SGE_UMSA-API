package com.sgeumsaapi.sge_umsa_api.DTO.evento;



import java.time.LocalDateTime;

public class EventoCreateDTO {
    private String titulo;
    private String descripcion;
    private String tipoEvento;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinal;
    private String organizadorCi;

    // Getters y Setters
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

    public String getOrganizadorCi() {
        return organizadorCi;
    }

    public void setOrganizadorCi(String organizadorCi) {
        this.organizadorCi = organizadorCi;
    }
}