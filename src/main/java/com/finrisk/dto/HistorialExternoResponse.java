package com.finrisk.dto;

import java.math.BigDecimal;

public class HistorialExternoResponse {

    private Integer externoId;
    private String dni;
    private String nombre;
    private String apellido;
    private BigDecimal deudaTotal;
    private Integer numeroEmpresas;
    private Integer diasMora;

    public Integer getExternoId() {
        return externoId;
    }

    public void setExternoId(Integer externoId) {
        this.externoId = externoId;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public BigDecimal getDeudaTotal() {
        return deudaTotal;
    }

    public void setDeudaTotal(BigDecimal deudaTotal) {
        this.deudaTotal = deudaTotal;
    }

    public Integer getNumeroEmpresas() {
        return numeroEmpresas;
    }

    public void setNumeroEmpresas(Integer numeroEmpresas) {
        this.numeroEmpresas = numeroEmpresas;
    }

    public Integer getDiasMora() {
        return diasMora;
    }

    public void setDiasMora(Integer diasMora) {
        this.diasMora = diasMora;
    }
}
