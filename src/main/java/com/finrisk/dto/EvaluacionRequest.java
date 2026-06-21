package com.finrisk.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class EvaluacionRequest {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotBlank(message = "El DNI del cliente es obligatorio")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 caracteres")
    @Pattern(regexp = "^[0-9]+$", message = "El DNI debe contener solo números")
    private String dniCliente;

    @NotNull(message = "Los ingresos mensuales son obligatorios")
    @DecimalMin(value = "0.01", message = "Los ingresos deben ser mayores que 0")
    private BigDecimal ingresosMensuales;

    @NotNull(message = "Las deudas actuales son obligatorias")
    @DecimalMin(value = "0.00", message = "Las deudas no pueden ser negativas")
    private BigDecimal deudasActuales;
    
    private String estadoSolicitud;
    
    
    
    
    public String getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
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