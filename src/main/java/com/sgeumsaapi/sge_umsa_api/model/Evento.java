package com.sgeumsaapi.sge_umsa_api.model;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="evento")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
    
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_evento;

    @Version
    private Long version;
    @Column(nullable = false, length = 60)
    @NotBlank(message = "El titulo no puede estar vacio")
    @Length(min = 5,max = 60, message = "El titulo debe tener entre 5 y 60 caracteres")
    private String titulo;

    @Column(nullable = false, length = 1000)
    @NotBlank(message = "agregue una descripcion")
    private String descripcion;

    @Column(nullable = false)
    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDateTime fecha_inicio;

    @Column(nullable = false)
    @NotNull(message = "La fecha del final es obligatoria")
    private LocalDateTime fecha_final;

    @Column(nullable = false, length = 30)
    @NotBlank(message = "El tipo no puede estar vacio")
    @Length(min = 5,max = 30, message = "El tipo debe tener entre 5 y 30 caracteres")
    private String tipo;

    @Column(nullable = false, length = 30)
    @NotBlank(message = "El estado no puede estar vacio")
    @Length(min = 5,max = 30, message = "El estado debe tener entre 5 y 30 caracteres")
    private String estado;

    @Column(nullable = false)
    @Min(value = 1,message = "Agrege el ci del organizador")
    @NotNull(message = "Debe indicar el CI del organizador")
    private Long organizador;
}
