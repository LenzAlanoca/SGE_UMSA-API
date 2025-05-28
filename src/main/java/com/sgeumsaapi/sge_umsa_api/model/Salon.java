package com.sgeumsaapi.sge_umsa_api.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "salon")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Salon {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_salon;
    
    @Version
    private Long version;

    @Column (nullable = false,length = 100)
    @NotBlank(message = "Debe agregar un nombre")
    @Size(max=100, message = "El nombre no debe superar 100 caracteres")
    private String nombre;

    @Min(value = 1,message = "La capacidad debe ser al menos 1")
    private Integer capacidad;
    
    @ManyToOne
    @JoinColumn(name = "id_predio",nullable = false)
    private Predio predio;
}
