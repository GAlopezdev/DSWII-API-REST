package com.finrisk.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "evaluaciones_financieras")
public class EvaluacionFinanciera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evalucion") // Mapeado exacto a tu PK de la BD
    private Integer idEvaluacion;

    @Column(name = "nombre_cliente", nullable = false, length = 150)
    private String nombrePersona;

    @Column(name = "ingresos_mensuales", nullable = false)
    private Double sueldoMensual;

    @Column(name = "deudas_actuales", nullable = false)
    private Double deudasMensuales;

    @Column(name = "resultado_riesgo", length = 20)
    private String resultadoRiesgo; // "ALTO" o "BAJO"

    // --- NUEVOS CAMPOS DE LA BASE DE DATOS REAL (Para que no falle al leer) ---
    @Column(name = "dni_cliente", nullable = false, length = 20)
    private String dniCliente;

    @Column(name = "puntaje_score")
    private Integer puntajeScore;

    @Column(name = "estado_solicitud", length = 50)
    private String estadoSolicitud;

    // --- GETTERS Y SETTERS MANUALES ---

    public Integer getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public Double getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(Double sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    public Double getDeudasMensuales() {
        return deudasMensuales;
    }

    public void setDeudasMensuales(Double deudasMensuales) {
        this.deudasMensuales = deudasMensuales;
    }

    public String getResultadoRiesgo() {
        return resultadoRiesgo;
    }

    public void setResultadoRiesgo(String resultadoRiesgo) {
        this.resultadoRiesgo = resultadoRiesgo;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public Integer getPuntajeScore() {
        return puntajeScore;
    }

    public void setPuntajeScore(Integer puntajeScore) {
        this.puntajeScore = puntajeScore;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }
}