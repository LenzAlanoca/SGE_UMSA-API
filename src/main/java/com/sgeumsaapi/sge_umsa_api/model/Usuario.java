package com.sgeumsaapi.sge_umsa_api.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @Column(nullable = false, unique = true)
    private Long ci;

    @Version
    private Long version;

    @Column(nullable = false, length = 50)
    @Basic(optional = false)
    @NotBlank(message = "El nombre no puede estar vacío")
    @Length(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @Column(nullable = false, length = 50)
    @Basic(optional = false)
    @NotBlank(message = "El apellido no puede estar vacío")
    @Length(min = 3, max = 50, message = "El apellido debe tener entre 3 y 50 caracteres")
    private String apellido;

    @Column(nullable = false, unique = true)
    @Basic(optional = false)
    @NotBlank(message = "El correo no puede estar vacío")
    @Email
    private String correo;

    @NotBlank(message = "El usuario no puede estar vacío")
    @Column(nullable = false, length = 20)
    @Length(min = 3, max = 20, message = "El usuario debe tener entre 3 y 20 caracteres")
    private String usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Rol rol;

    @Column(nullable = false, length = 15)
    @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 caracteres")
    private String telefono;

    private String foto;
}
