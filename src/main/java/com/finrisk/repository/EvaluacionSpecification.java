package com.finrisk.repository;

import org.springframework.data.jpa.domain.Specification;
import com.finrisk.entity.Evaluacion;
import com.finrisk.entity.Usuario;

public final class EvaluacionSpecification {

    public EvaluacionSpecification() {
        super();
    }

    public static Specification<Evaluacion> usuarioEquals(Usuario usuario) {
        return (root, query, cb) -> cb.equal(root.get("usuario"), usuario);
    }

    public static Specification<Evaluacion> dniClienteEquals(String dni) {
        return (root, query, cb) -> 
            cb.equal(root.join("historialExterno").get("dni"), dni);
    }

    public static Specification<Evaluacion> nombreClienteContains(String nombre) {
        return (root, query, cb) -> {
            var he = root.join("historialExterno");
            String pattern = "%" + nombre.toLowerCase() + "%";
            return cb.or(
                cb.like(cb.lower(he.get("nombre")), pattern),
                cb.like(cb.lower(he.get("apellido")), pattern)
            );
        };
    }

    public static Specification<Evaluacion> estadoEquals(String estado) {
        return (root, query, cb) -> cb.equal(root.get("estado"), estado);
    }

    public static Specification<Evaluacion> fechaEvaluacionBetween(java.time.LocalDate inicio, java.time.LocalDate fin) {
        return (root, query, cb) -> {
            if (inicio != null && fin != null) {
                return cb.between(root.get("fechaEvaluacion"), inicio.atStartOfDay(), fin.atTime(23, 59, 59));
            } else if (inicio != null) {
                return cb.greaterThanOrEqualTo(root.get("fechaEvaluacion"), inicio.atStartOfDay());
            } else {
                return cb.lessThanOrEqualTo(root.get("fechaEvaluacion"), fin.atTime(23, 59, 59));
            }
        };
    }
}
