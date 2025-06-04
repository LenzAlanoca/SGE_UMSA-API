package com.sgeumsaapi.sge_umsa_api.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "ASIGNADO")
public class Asignado {
    @EmbeddedId
    private AsignadoId id;

    @ManyToOne
    @MapsId("idSalon")
    @JoinColumn(name = "id_salon", referencedColumnName = "id_salon")
    private Salon salon;

    @ManyToOne
    @MapsId("idEvento")
    @JoinColumn(name = "id_evento", referencedColumnName = "id_evento")
    private Evento evento;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;

    @Column(name = "fecha_asignacion")
    private LocalDateTime fechaAsignacion;

    // Getters y Setters
    public AsignadoId getId() {
        return id;
    }

    public void setId(AsignadoId id) {
        this.id = id;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }
}