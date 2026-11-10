package com.jcaa.usersmanagement.domain.exception;

/**
 * Excepción de Dominio: Se dispara cuando se intenta realizar una acción
 * no permitida sobre un artículo que ha sido previamente rechazado.
 */
public class ArticuloRechazadoException extends RuntimeException {
    
    // Constructor que transmite el mensaje de error de la regla de negocio violada
    public ArticuloRechazadoException(String message) {
        super(message);
    }
}