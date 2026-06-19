package com.finrisk.dto;

import java.math.BigDecimal;

public class EvaluacionRequest {
    private String nombreCliente;
    private String dniCliente;
    private BigDecimal ingresosMensuales;
    private BigDecimal deudasActuales;
    private Integer puntajeScore;

    public Integer getPuntajeScore() {
        return puntajeScore;
    }

    public void setPuntajeScore(Integer puntajeScore) {
        this.puntajeScore = puntajeScore;
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

    public BigDecimal getDeudasActuales() {
        return deudasActuales;
    }

    public void setDeudasActuales(BigDecimal deudasActuales) {
        this.deudasActuales = deudasActuales;
    }

    public BigDecimal getIngresosMensuales() {
        return ingresosMensuales;
    }

    public void setIngresosMensuales(BigDecimal ingresosMensuales) {
        this.ingresosMensuales = ingresosMensuales;
    }
}