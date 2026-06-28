package com.finrisk.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class HistorialExternoRequest {

    @NotBlank(message = "El DNI es obligatorio")
    @Size(min = 8, max = 20, message = "El DNI debe tener entre 8 y 20 caracteres")
    @Pattern(regexp = "^[0-9]+$", message = "El DNI debe contener solo números")
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotNull(message = "La deuda total es obligatoria")
    private BigDecimal deudaTotal;

    @NotNull(message = "El número de empresas es obligatorio")
    @Min(value = 0, message = "El número de empresas no puede ser negativo")
    private Integer numeroEmpresas;

    @NotNull(message = "Los días de mora son obligatorios")
    @Min(value = 0, message = "Los días de mora no pueden ser negativos")
    private Integer diasMora;

    @NotNull(message = "El sueldo es obligatorio")
    @Min(value = 0, message = "El sueldo no puede ser negativo")
    private BigDecimal sueldo;

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

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }
}
