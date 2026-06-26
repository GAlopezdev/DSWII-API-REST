package com.finrisk.dto;

import java.time.LocalDateTime;

public class EvaluacionResponse {

    private Integer evaluacionId;

    private String dniCliente;
    private String nombreCliente;
    private String apellidoCliente;

    private Integer productoId;
    private String nombreProducto;
    private Integer scoreMinimo;

    private String emailAsesor;

    private Integer scoreObtenido;
    private String estado;
    private LocalDateTime fechaEvaluacion;
    private String comentarios;

    public Integer getEvaluacionId() {
        return evaluacionId;
    }

    public void setEvaluacionId(Integer evaluacionId) {
        this.evaluacionId = evaluacionId;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getScoreMinimo() {
        return scoreMinimo;
    }

    public void setScoreMinimo(Integer scoreMinimo) {
        this.scoreMinimo = scoreMinimo;
    }

    public String getEmailAsesor() {
        return emailAsesor;
    }

    public void setEmailAsesor(String emailAsesor) {
        this.emailAsesor = emailAsesor;
    }

    public Integer getScoreObtenido() {
        return scoreObtenido;
    }

    public void setScoreObtenido(Integer scoreObtenido) {
        this.scoreObtenido = scoreObtenido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(LocalDateTime fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
