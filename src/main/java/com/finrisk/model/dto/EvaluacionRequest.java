package com.empresa.model.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class EvaluacionRequest {
    private String nombreCliente;
    private String dniCliente;
    private BigDecimal ingresosMensuales;
    private BigDecimal deudasActuales;
    private Integer puntajeScore;
}