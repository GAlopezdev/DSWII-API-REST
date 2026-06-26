package com.finrisk.mapper;

import com.finrisk.dto.EvaluacionResponse;
import com.finrisk.entity.Evaluacion;
import com.finrisk.entity.HistorialExterno;
import com.finrisk.entity.ProductoCredito;
import org.springframework.stereotype.Component;

@Component
public class EvaluacionMapper {

    public EvaluacionResponse toResponse(Evaluacion evaluacion) {
        if (evaluacion == null) return null;
        
        EvaluacionResponse response = new EvaluacionResponse();
        response.setEvaluacionId(evaluacion.getEvaluacionId());

        HistorialExterno h = evaluacion.getHistorialExterno();
        if (h != null) {
            response.setDniCliente(h.getDni());
            response.setNombreCliente(h.getNombre());
            response.setApellidoCliente(h.getApellido());
        }

        ProductoCredito p = evaluacion.getProductoCredito();
        if (p != null) {
            response.setProductoId(p.getProductoId());
            response.setNombreProducto(p.getNombreProducto());
            response.setScoreMinimo(p.getScoreMinimo());
        }

        if (evaluacion.getUsuario() != null) {
            response.setEmailAsesor(evaluacion.getUsuario().getEmail());
        }
        
        response.setScoreObtenido(evaluacion.getScoreObtenido());
        response.setEstado(evaluacion.getEstado());
        response.setFechaEvaluacion(evaluacion.getFechaEvaluacion());
        response.setComentarios(evaluacion.getComentarios());

        return response;
    }
}