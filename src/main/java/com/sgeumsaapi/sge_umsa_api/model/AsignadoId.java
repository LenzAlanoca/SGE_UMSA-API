package com.sgeumsaapi.sge_umsa_api.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class AsignadoId implements Serializable {
    private Long idSalon;
    private Long idEvento;




    public AsignadoId(Long idSalon, Long idEvento) {
        this.idSalon = idSalon;
        this.idEvento = idEvento;
    }

    // Getters y Setters
    public Long getIdSalon() {
        return idSalon;
    }

    public void setIdSalon(Long idSalon) {
        this.idSalon = idSalon;
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

        AsignadoId that = (AsignadoId) o;

        if (!idSalon.equals(that.idSalon)) return false;
        return idEvento.equals(that.idEvento);
    }

    @Override
    public int hashCode() {
        int result = idSalon.hashCode();
        result = 31 * result + idEvento.hashCode();
        return result;
    }
}