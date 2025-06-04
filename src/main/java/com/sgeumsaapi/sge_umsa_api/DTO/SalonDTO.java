package com.sgeumsaapi.sge_umsa_api.DTO;

import lombok.Data;

@Data
public class SalonDTO {
    private Long id;
    private String nombre;
    private Integer capacidad;
    private String ubicacion;
    private Long predioId;

    public SalonDTO(Long id, String nombre, Integer capacidad, String ubicacion, Long predioId) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.predioId = predioId;
    }
    


    
}
