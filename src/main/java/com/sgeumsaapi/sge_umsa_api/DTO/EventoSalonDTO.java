package com.sgeumsaapi.sge_umsa_api.DTO;

import lombok.Data;

@Data
public class EventoSalonDTO {
    private EventoSalonIdDTO id;
    private Long eventoId;
    private Long salonId;
    private String observacion;
}
