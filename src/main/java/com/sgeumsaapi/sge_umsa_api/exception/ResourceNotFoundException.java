package com.sgeumsaapi.sge_umsa_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada para manejar recursos no encontrados en la aplicación.
 */
@ResponseStatus(HttpStatus.NOT_FOUND) // Automáticamente devuelve código 404
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
