package com.sgeumsaapi.sge_umsa_api.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class AsistenciaId implements Serializable {
    private String ci;
    private Long idEvento;

    // Getters y Setters
    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AsistenciaId that = (AsistenciaId) o;

        if (!ci.equals(that.ci)) return false;
        return idEvento.equals(that.idEvento);
    }

    @Override
    public int hashCode() {
        int result = ci.hashCode();
        result = 31 * result + idEvento.hashCode();
        return result;
    }
}