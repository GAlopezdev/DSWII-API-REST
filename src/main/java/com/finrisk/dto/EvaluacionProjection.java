package com.finrisk.dto;

import org.springframework.beans.factory.annotation.Value;
import java.time.LocalDateTime;

public interface EvaluacionProjection {

    Integer getEvaluacionId();
    
    Integer getScoreObtenido();
    
    String getEstado();
    
    LocalDateTime getFechaEvaluacion();
    
    String getComentarios();
    
    @Value("#{target.historialExterno.dni}")
    String getDniCliente();
    
    @Value("#{target.historialExterno.nombre + ' ' + target.historialExterno.apellido}")
    String getNombreCliente();
    
    @Value("#{target.productoCredito?.nombreProducto}")
    String getProducto();
}
