package com.sgeumsaapi.sge_umsa_api.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoSalonId implements Serializable {
    
    @Column(name = "id_evento")
    private Long idEvento;

    @Column(name = "id_salon")
    private Long idSalon;
}
