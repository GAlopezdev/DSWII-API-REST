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
		entity.setDeudasMensuales(request.getIngresosMensuales());
		entity.setDeudasActuales(request.getDeudasActuales());
		entity.setPuntajeScore(request.getPuntajeScore());
		return entity;
	}

	public EvaluacionResponse toResponse(EvaluacionFinanciera entity) {
		EvaluacionResponse response = new EvaluacionResponse();
		response.setIdEvalucion(entity.getIdEvalucion());
		response.setNombreCliente(entity.getNombreCliente());
		response.setDniCliente(entity.getDniCliente());
		response.setIngresosMensuales(entity.getIngresosMensuales());
		response.setDeudasActuales(entity.getDeudasActuales());
		response.setPuntajeScore(entity.getPuntajeScore());
		response.setResultadoRiesgo(entity.getResultadoRiesgo());
		response.setEstadoSolicitud(entity.getEstadoSolicitud());
		return response;
	}
}