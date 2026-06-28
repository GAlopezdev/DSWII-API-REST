package com.finrisk.repository;

import org.springframework.data.jpa.domain.Specification;
import com.finrisk.entity.HistorialExterno;

public final class HistorialExternoSpecification {

    public HistorialExternoSpecification() {
        super();
    }

    public static Specification<HistorialExterno> nombreContains(String nombre) {
        return (root, query, cb) -> 
            cb.like(
                cb.upper(root.get("nombre")), 
                "%" + nombre.toUpperCase() + "%" 
            );
    }
    
    public static Specification<HistorialExterno> apellidoContains(String apellido) {
        return (root, query, cb) -> 
            cb.like(
                cb.upper(root.get("apellido")), 
                "%" + apellido.toUpperCase() + "%" 
            );
    }

    public static Specification<HistorialExterno> dniEquals(String dni) {
        return (root, query, cb) ->
            cb.equal(root.get("dni"), dni);
    }
}
