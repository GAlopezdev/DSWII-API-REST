package com.finrisk.service;

import com.finrisk.mapper.EvaluacionMapper;
import com.finrisk.dto.EvaluacionRequest;
import com.finrisk.dto.EvaluacionResponse;
import com.finrisk.entity.EvaluacionFinanciera;
import com.finrisk.repository.EvaluacionRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

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

        BigDecimal ingresos = request.getIngresosMensuales();
        BigDecimal deudas = request.getDeudasActuales();

        int score = calcularScore(ingresos, deudas);
        String riesgo = determinarRiesgo(score);

        evaluacion.setPuntajeScore(score);
        evaluacion.setResultadoRiesgo(riesgo);
        evaluacion.setEstadoSolicitud("PENDIENTE");

		EvaluacionFinanciera evaluacionGuardada = evaluacionRepository.save(evaluacion);
		return evaluacionMapper.toResponse(evaluacionGuardada);
	}

    private int calcularScore(BigDecimal ingresos, BigDecimal deudas) {
        if (ingresos.compareTo(BigDecimal.ZERO) == 0 || deudas.compareTo(ingresos) >= 0) {
            return 0;
        }
        BigDecimal libre = ingresos.subtract(deudas);
        BigDecimal porcentaje = libre.divide(ingresos, 4, RoundingMode.HALF_UP);
        return porcentaje.multiply(new BigDecimal("1000")).setScale(0, RoundingMode.HALF_UP).intValue();
    }

    private String determinarRiesgo(int score) {
        if (score >= 700) return "BAJO";
        else if (score >= 300) return "MEDIO";
        else return "ALTO";
    }
}