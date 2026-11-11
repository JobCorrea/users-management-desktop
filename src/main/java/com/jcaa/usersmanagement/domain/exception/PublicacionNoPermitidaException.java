package com.jcaa.usersmanagement.domain.exception;

/**
 * Excepción de Dominio: Se dispara cuando el Servicio de Dominio 
 * detecta que el contenido incumple las políticas de publicación o moderación.
 */
public class PublicacionNoPermitidaException extends RuntimeException {
    
    // Transmite el motivo del bloqueo (ej. detección de spam o palabras prohibidas)
    public PublicacionNoPermitidaException(String message) {
        super(message);
    }
}