package com.finrisk.mapper;

import com.finrisk.dto.EvaluacionRequest;
import com.finrisk.dto.EvaluacionResponse;
import com.finrisk.entity.EvaluacionFinanciera;
import org.springframework.stereotype.Component;

@Component
public class EvaluacionMapper {

    public EvaluacionFinanciera toEntity(EvaluacionRequest request) {
        EvaluacionFinanciera entity = new EvaluacionFinanciera();
        entity.setNombrePersona(request.getNombreCliente());
        entity.setDniCliente(request.getDniCliente());
        entity.setSueldoMensual(request.getIngresosMensuales());
        entity.setDeudasMensuales(request.getDeudasActuales());
        entity.setPuntajeScore(request.getPuntajeScore());
        return entity;
    }

    public EvaluacionResponse toResponse(EvaluacionFinanciera entity) {
        EvaluacionResponse response = new EvaluacionResponse();
        response.setIdEvalucion(entity.getIdEvaluacion().longValue());
        response.setNombreCliente(entity.getNombrePersona());
        response.setDniCliente(entity.getDniCliente());
        response.setIngresosMensuales(entity.getSueldoMensual());
        response.setDeudasActuales(entity.getDeudasMensuales());
        response.setPuntajeScore(entity.getPuntajeScore());
        response.setResultadoRiesgo(entity.getResultadoRiesgo());
        response.setEstadoSolicitud(entity.getEstadoSolicitud());
        return response;
    }
}