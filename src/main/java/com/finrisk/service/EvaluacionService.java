package com.finrisk.service;

import com.finrisk.mapper.EvaluacionMapper;
import com.finrisk.dto.EvaluacionRequest;
import com.finrisk.dto.EvaluacionResponse;
import com.finrisk.entity.EvaluacionFinanciera;
import com.finrisk.repository.EvaluacionRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class EvaluacionService {

	private final EvaluacionRepository evaluacionRepository;
	private final EvaluacionMapper evaluacionMapper;

    public EvaluacionService(EvaluacionRepository evaluacionRepository, EvaluacionMapper evaluacionMapper) {
        this.evaluacionRepository = evaluacionRepository;
        this.evaluacionMapper = evaluacionMapper;
    }

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