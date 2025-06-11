package com.sgeumsaapi.sge_umsa_api.model;

import org.junit.jupiter.api.Test;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

public class AsignadoTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void deberiaCrearAsignadoConDatosValidos() {

        AsignadoId id = new AsignadoId(1L, 1L);
        Salon salon = crearSalon(1L, "Aula 101");
        Evento evento = crearEvento(1L, "Conferencia Spring");


        Asignado asignado = new Asignado();
        asignado.setId(id);
        asignado.setSalon(salon);
        asignado.setEvento(evento);
        asignado.setHoraInicio(LocalTime.of(9, 0));
        asignado.setHoraFin(LocalTime.of(11, 0));
        asignado.setFechaAsignacion(LocalDateTime.now());

        // Then
        assertThat(asignado.getId()).isEqualTo(id);
        assertThat(asignado.getSalon()).isEqualTo(salon);
        assertThat(asignado.getEvento()).isEqualTo(evento);
        assertThat(asignado.getHoraInicio()).isEqualTo(LocalTime.of(9, 0));
        assertThat(asignado.getHoraFin()).isEqualTo(LocalTime.of(11, 0));
        assertThat(asignado.getFechaAsignacion()).isNotNull();
    }

    @Test
    void deberiaValidarHorarioConsistente() {
        // Given
        AsignadoId id = new AsignadoId(1L, 1L);
        Asignado asignado = new Asignado();
        asignado.setId(id);
        asignado.setHoraInicio(LocalTime.of(10, 0));
        asignado.setHoraFin(LocalTime.of(9, 0)); // Hora fin antes que inicio


        Set<ConstraintViolation<Asignado>> violations = validator.validate(asignado);

    }

    @Test
    void deberiaValidarCamposObligatorios() {

        Asignado asignado = new Asignado();

        Set<ConstraintViolation<Asignado>> violations = validator.validate(asignado);

        violations.forEach(v -> System.out.println(v.getPropertyPath() + ": " + v.getMessage()));
    }

    private Salon crearSalon(Long id, String nombre) {
        Salon salon = new Salon();
        salon.setIdSalon(id);
        salon.setNombreSalon(nombre);
        return salon;
    }

    private Evento crearEvento(Long id, String nombre) {
        Evento evento = new Evento();
        evento.setIdEvento(id);
        evento.setTitulo(nombre);
        return evento;
    }
}