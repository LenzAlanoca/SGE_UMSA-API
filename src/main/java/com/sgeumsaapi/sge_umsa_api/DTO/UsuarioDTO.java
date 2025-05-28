package com.sgeumsaapi.sge_umsa_api.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    
    /** Cédula de identidad, identificador único */
    @NotNull(message = "El ci no puede ser nulo")
    private Long ci;

    /** Nombre del usuario */
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    /** Apellido del usuario */
    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 3, max = 50, message = "El apellido debe tener entre 3 y 50 caracteres")
    private String apellido;

    /** Correo electrónico del usuario */
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo no es válido")
    private String correo;

    /** Nombre de usuario para login */
    @NotBlank(message = "El usuario no puede estar vacío")
    @Size(min = 3, max = 20, message = "El usuario debe tener entre 3 y 20 caracteres")
    private String usuario;

    /** Rol del usuario */
    @NotBlank(message = "El rol no puede estar vacío")
    @Size(min = 3, max = 20, message = "El rol debe tener entre 3 y 20 caracteres")
    private String rol;

    /** Teléfono del usuario */
    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 caracteres")
    private String telefono;

    /** Foto del usuario (ruta o URL), opcional */
    private String foto;
}
