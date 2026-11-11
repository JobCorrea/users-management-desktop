package com.jcaa.usersmanagement.domain.service;

import com.jcaa.usersmanagement.domain.exception.PublicacionNoPermitidaException;
import java.util.List;

/**
 * Servicio de Dominio (Domain Service):
 * Contiene lógica de negocio pura que no encaja naturalmente dentro de una sola entidad.
 * Se encarga de auditar y validar el contenido de publicaciones/comentarios.
 */
public class ModeracionContenidoService {

    // Lista inmutable de palabras no permitidas dentro de la plataforma (regla de dominio)
    private static final List<String> PALABRAS_PROHIBIDAS = List.of("spam", "ofensivo", "ilegal", "estafa");

    /**
     * Regla de Negocio Cruzada:
     * Analiza el texto proporcionado y lanza una excepción si contiene términos prohibidos.
     * 
     * @param textoContenido El texto a moderar antes de ser procesado por el Agregado.
     */
    public void validarContenido(String textoContenido) {
        if (textoContenido == null || textoContenido.trim().isEmpty()) {
            throw new PublicacionNoPermitidaException("El contenido de la publicación no puede estar vacío.");
        }

        String textoEnMinusculas = textoContenido.toLowerCase();
        
        for (String palabra : PALABRAS_PROHIBIDAS) {
            if (textoEnMinusculas.contains(palabra)) {
                throw new PublicacionNoPermitidaException("El contenido contiene términos prohibidos por moderación: " + palabra);
            }
        }
    }
}