package com.jcaa.usersmanagement.domain.model;

import java.util.Objects;

/**
 * Value Object: Representa la identidad única e inmutable de un Artículo.
 * Evita el uso de tipos primitivos sueltos (como Long o String) para garantizar tipado fuerte.
 */
public final class ArticuloId {
    
    // Atributo final para asegurar la inmutabilidad de la identidad
    private final Long value;

    // Validación de negocio: Un ID debe ser numérico y positivo
    public ArticuloId(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("El ArticuloId debe ser un número positivo.");
        }
        this.value = value;
    }

    // Getter del valor encapsulado
    public Long getValue() {
        return value;
    }

    // Comparación estructural de la identidad (por valor, no por referencia en memoria)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticuloId that = (ArticuloId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}