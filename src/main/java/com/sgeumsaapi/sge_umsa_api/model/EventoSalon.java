package com.sgeumsaapi.sge_umsa_api.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evento_salon")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoSalon {
    
    @EmbeddedId
    private EventoSalonId id = new EventoSalonId();
    
    @ManyToOne
    @MapsId("idEvento")
    @JoinColumn(name = "id_evento")
    private Evento evento;

    @ManyToOne
    @MapsId("idSalon")
    @JoinColumn(name = "id_salon")
    private Salon salon;

    @Column(name = "fecha_asignacion", nullable = false)
    @NotNull(message = "La fecha de asignación no puede ser nula")
    private LocalDate fechaAsignacion;
}
