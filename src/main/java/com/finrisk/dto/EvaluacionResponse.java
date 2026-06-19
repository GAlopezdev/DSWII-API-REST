package com.finrisk.dto;

import java.math.BigDecimal;

public class EvaluacionResponse {
	private Long idEvalucion;
	private String nombreCliente;
	private String dniCliente;
	private BigDecimal ingresosMensuales;
	private BigDecimal deudasActuales;
	private Integer puntajeScore;
	private String resultadoRiesgo;
	private String estadoSolicitud;

    public Long getIdEvalucion() {
        return idEvalucion;
    }

    public void setIdEvalucion(Long idEvalucion) {
        this.idEvalucion = idEvalucion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public BigDecimal getIngresosMensuales() {
        return ingresosMensuales;
    }

    public void setIngresosMensuales(BigDecimal ingresosMensuales) {
        this.ingresosMensuales = ingresosMensuales;
    }

    public BigDecimal getDeudasActuales() {
        return deudasActuales;
    }

    public void setDeudasActuales(BigDecimal deudasActuales) {
        this.deudasActuales = deudasActuales;
    }

    public Integer getPuntajeScore() {
        return puntajeScore;
    }

    public void setPuntajeScore(Integer puntajeScore) {
        this.puntajeScore = puntajeScore;
    }

    public String getResultadoRiesgo() {
        return resultadoRiesgo;
    }

    public void setResultadoRiesgo(String resultadoRiesgo) {
        this.resultadoRiesgo = resultadoRiesgo;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }
}