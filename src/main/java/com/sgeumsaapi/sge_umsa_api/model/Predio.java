package com.sgeumsaapi.sge_umsa_api.model;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "predio")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Predio {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_predio;

    @Version
    private Long version;

    @Column (nullable = false,length = 100)
    @NotBlank(message = "Debe agregar un nombre")
    @Size(max=100, message = "El nombre no debe superar100 caracteres")
    private String nombre;

    @Column(length = 200)
    @Size(max = 200,message = "La ubicacion no debe superar los 200 caracteres")
    private String ubicacion;

    @OneToMany(mappedBy = "predio",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Salon> salones;
}
