package com.empresa.model.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class EvaluacionResponse {
	private Long idEvalucion;
	private String nombreCliente;
	private String dniCliente;
	private BigDecimal ingresosMensuales;
	private BigDecimal deudasActuales;
	private Integer puntajeScore;
	private String resultadoRiesgo;
	private String estadoSolicitud;
}