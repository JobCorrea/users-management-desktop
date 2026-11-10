package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.exception.InvalidEmailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    @DisplayName("Debería lanzar InvalidEmailException cuando el formato de correo sea inválido")
    void deberiaLanzarExcepcionCuandoEmailEsInvalido() {
        String correoInvalido = "correo-sin-formato";

        assertThrows(InvalidEmailException.class, () -> {
            new Email(correoInvalido);
        });
    }

    @Test
    @DisplayName("Debería instanciar el VO correctamente cuando el correo es válido")
    void deberiaCrearEmailValido() {
        String correoValido = "job.correa@unicartagena.edu.co";
        Email email = new Email(correoValido);

        assertNotNull(email);
        assertEquals(correoValido, email.getValue());
    }
}