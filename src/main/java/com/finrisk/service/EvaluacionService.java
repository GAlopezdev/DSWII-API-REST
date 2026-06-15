package com.empresa.service;

import com.empresa.mapper.EvaluacionMapper;
import com.empresa.model.dto.EvaluacionRequest;
import com.empresa.model.dto.EvaluacionResponse;
import com.empresa.model.entity.EvaluacionFinanciera;
import com.empresa.repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class EvaluacionService {

	@Autowired
	private EvaluacionRepository evaluacionRepository;

	@Autowired
	private EvaluacionMapper evaluacionMapper;

	public EvaluacionResponse crearEvaluacion(EvaluacionRequest request) {
		EvaluacionFinanciera evaluacion = evaluacionMapper.toEntity(request);

		BigDecimal mitadSueldo = request.getIngresosMensuales().divide(new BigDecimal("2"));

		if (request.getDeudasActuales().compareTo(mitadSueldo) > 0) {
			evaluacion.setResultadoRiesgo("ALTO");
		} else {
			evaluacion.setResultadoRiesgo("BAJO");
		}

		evaluacion.setEstadoSolicitud("PENDIENTE");

		EvaluacionFinanciera evaluacionGuardada = evaluacionRepository.save(evaluacion);

		return evaluacionMapper.toResponse(evaluacionGuardada);
	}
}