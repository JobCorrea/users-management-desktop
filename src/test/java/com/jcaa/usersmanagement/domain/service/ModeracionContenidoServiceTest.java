package com.jcaa.usersmanagement.domain.service;

import com.jcaa.usersmanagement.domain.exception.PublicacionNoPermitidaException;
import com.jcaa.usersmanagement.domain.service.ModeracionContenidoService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba Unitaria Pura  para el Servicio de Dominio ModeracionContenidoService.
 */
class ModeracionContenidoServiceTest {

    private final ModeracionContenidoService moderacionService = new ModeracionContenidoService();

    @Test
    @DisplayName("Debería lanzar PublicacionNoPermitidaException cuando el texto contenga palabras prohibidas")
    void deberiaLanzarExcepcionSiContenidoContieneSpam() {
        // Arrange: Texto que violará la regla de moderación del dominio
        String textoConSpam = "Gana dinero rápido con esta estafa garantizada";

        // Act & Assert: Verificamos que el servicio interrumpa el proceso al detectar la palabra prohibida
        assertThrows(PublicacionNoPermitidaException.class, () -> {
            moderacionService.validarContenido(textoConSpam);
        });
    }

    @Test
    @DisplayName("Debería permitir la publicación si el contenido cumple con las normas del dominio")
    void deberiaPermitirContenidoValido() {
        // Arrange: Texto limpio y adecuado para la red social
        String textoValido = "Este es un artículo técnico sobre arquitectura Hexagonal y DDD.";

        // Act & Assert: Se comprueba que no lance ninguna excepción de dominio
        assertDoesNotThrow(() -> {
            moderacionService.validarContenido(textoValido);
        });
    }
}