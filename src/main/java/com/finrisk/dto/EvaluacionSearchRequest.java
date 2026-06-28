package com.finrisk.dto;

public class EvaluacionSearchRequest {
    private String dni;

    private String nombreCliente;
    private String estado;
    private java.time.LocalDate fechaInicio;
    private java.time.LocalDate fechaFin;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public java.time.LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(java.time.LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public java.time.LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(java.time.LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
