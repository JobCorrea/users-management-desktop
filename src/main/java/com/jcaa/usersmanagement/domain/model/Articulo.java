package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.exception.ArticuloRechazadoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Raíz de Agregado (Aggregate Root): Modela la entidad principal Articulo dentro del dominio.
 * Su responsabilidad principal es mantener la consistencia interna de los datos y 
 * proteger las reglas e invariantes del negocio.
 */
public class Articulo {

    // Identificador único del agregado (representado mediante el Value Object ArticuloId)
    private final ArticuloId id;

    // Estado interno del artículo (indica si la publicación fue rechazada por moderación)
    private boolean rechazado;

    // Colección interna encapsulada que almacena los comentarios asociados
    private final List<String> comentarios;

    /**
     * Constructor del Agregado: Inicializa la entidad garantizando un estado inicial válido.
     * @param id Identificador único inmutable del artículo.
     */
    public Articulo(ArticuloId id) {
        // Validación de nulidad: Garantiza que un artículo nunca nazca sin una identidad válida
        this.id = Objects.requireNonNull(id, "El ID del artículo no puede ser nulo");
        this.rechazado = false;
        this.comentarios = new ArrayList<>();
    }

    /**
     * Regla de negocio: Cambia el estado del artículo a RECHAZADO.
     * Este método modifica la estructura interna permitiendo que otras reglas reaccionen.
     */
    public void rechazar() {
        this.rechazado = true;
    }

    /**
     * INVARIANTE DE NEGOCIO CRÍTICA:
     * Evalúa el estado de la publicación antes de modificar el agregado.
     * Si el artículo está en estado RECHAZADO, prohíbe la adición de nuevos comentarios 
     * lanzando la excepción de dominio ArticuloRechazadoException.
     * 
     * @param comentario Texto del nuevo comentario a publicar.
     */
    public void agregarComentario(String comentario) {
        if (this.rechazado) {
            throw new ArticuloRechazadoException("No se pueden agregar comentarios a un artículo en estado RECHAZADO.");
        }
        this.comentarios.add(comentario);
    }

    /**
     * Retorna una lista inmodificable de los comentarios.
     * Al usar unmodifiableList, evitamos que código externo modifique la lista desde afuera 
     * saltándose las reglas y validaciones de la Raíz de Agregado (Encapsulamiento DDD).
     */
    public List<String> getComentarios() {
        return Collections.unmodifiableList(comentarios);
    }

    // Getter para consultar el estado del artículo
    public boolean esRechazado() {
        return rechazado;
    }

    // Getter para obtener el Value Object del ID
    public ArticuloId getId() {
        return id;
    }
}