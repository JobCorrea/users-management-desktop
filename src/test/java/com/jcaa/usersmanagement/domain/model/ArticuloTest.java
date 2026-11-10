package com.jcaa.usersmanagement.domain.model;

// Imports de las clases del modelo de dominio
import com.jcaa.usersmanagement.domain.model.Articulo;
import com.jcaa.usersmanagement.domain.model.ArticuloId;
import com.jcaa.usersmanagement.domain.exception.ArticuloRechazadoException;

// Imports de JUnit 5
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba unitaria pura que verifica el cumplimiento de la invariante del Agregado Articulo.
 */
class ArticuloTest {

    @Test
    @DisplayName("Invariante: No debería permitir agregar comentarios si el artículo fue rechazado")
    void noDeberiaAgregarComentarioEnArticuloRechazado() {
        // 1. Arrange (Preparación): Instanciamos la Raíz de Agregado y la pasamos al estado 'rechazado'
        Articulo articulo = new Articulo(new ArticuloId(101L));
        articulo.rechazar();

        // 2. Act & Assert (Ejecución y Verificación): 
        // Verificamos que al llamar a 'agregarComentario' se dispare la excepción ArticuloRechazadoException
        assertThrows(ArticuloRechazadoException.class, () -> {
            articulo.agregarComentario("Comentario no permitido en publicación rechazada");
        });
    }

    @Test
    @DisplayName("Debería permitir agregar comentarios correctamente si el artículo está activo")
    void deberiaAgregarComentarioSiArticuloEstaActivo() {
        // Arrange
        Articulo articulo = new Articulo(new ArticuloId(102L));
        String comentarioValido = "Excelente publicación";

        // Act
        articulo.agregarComentario(comentarioValido);

        // Assert
        assertEquals(1, articulo.getComentarios().size());
        assertEquals(comentarioValido, articulo.getComentarios().get(0));
    }
}