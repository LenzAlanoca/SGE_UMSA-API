package com.sgeumsaapi.sge_umsa_api.DTO.lugar;



import java.util.List;

public class PredioDTO {
    private Long idPredio;
    private String nombrePredio;
    private String ubicacion;
    private List<SalonDTO> salones;

    // Getters y Setters
    public Long getIdPredio() {
        return idPredio;
    }

    public void setIdPredio(Long idPredio) {
        this.idPredio = idPredio;
    }

    public String getNombrePredio() {
        return nombrePredio;
    }

    public void setNombrePredio(String nombrePredio) {
        this.nombrePredio = nombrePredio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<SalonDTO> getSalones() {
        return salones;
    }

    public void setSalones(List<SalonDTO> salones) {
        this.salones = salones;
    }
}