package com.finrisk.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "historial_externo")
public class HistorialExterno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "externo_id")
    private Integer externoId;

    @Column(name = "dni", nullable = false, unique = true, length = 20)
    private String dni;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "deuda_total", precision = 10, scale = 2)
    private BigDecimal deudaTotal = BigDecimal.ZERO;

    @Column(name = "numero_empresas")
    private Integer numeroEmpresas = 0;

    @Column(name = "dias_mora")
    private Integer diasMora = 0;

    @Column(name = "sueldo", precision = 10, scale = 2)
    private BigDecimal sueldo = BigDecimal.ZERO;

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

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }
}
