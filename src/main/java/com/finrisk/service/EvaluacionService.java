package com.finrisk.service;

import com.finrisk.dto.CambioEstadoRequest;
import com.finrisk.entity.Empresa;
import com.finrisk.exception.BadCredentialsException;
import com.finrisk.exception.ResourceNotFound;
import com.finrisk.mapper.EvaluacionMapper;
import com.finrisk.dto.EvaluacionRequest;
import com.finrisk.dto.EvaluacionResponse;
import com.finrisk.entity.EvaluacionFinanciera;
import com.finrisk.repository.EmpresaRepository;
import com.finrisk.repository.EvaluacionRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class EvaluacionService {

	private final EvaluacionRepository evaluacionRepository;
	private final EvaluacionMapper evaluacionMapper;
    private final EmpresaRepository empresaRepository;

    public EvaluacionService(EvaluacionRepository evaluacionRepository, EvaluacionMapper evaluacionMapper, EmpresaRepository empresaRepository) {
        this.evaluacionRepository = evaluacionRepository;
        this.evaluacionMapper = evaluacionMapper;
        this.empresaRepository = empresaRepository;
    }

    private Empresa getEmpresaAutenticada() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return empresaRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Empresa no encontrada"));
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

        evaluacion.setEmpresa(getEmpresaAutenticada());

		EvaluacionFinanciera evaluacionGuardada = evaluacionRepository.save(evaluacion);
		return evaluacionMapper.toResponse(evaluacionGuardada);
	}

    public List<EvaluacionResponse> listarTodas() {
        Empresa empresa = getEmpresaAutenticada();
        return evaluacionRepository.findByEmpresa(empresa)
                .stream()
                .map(evaluacionMapper::toResponse)
                .toList();
    }

    public EvaluacionResponse obtenerPorId(Long id) {
        Empresa empresa = getEmpresaAutenticada();

        EvaluacionFinanciera evaluacion = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Evaluación no encontrada"));

        if (!evaluacion.getEmpresa().getIdEmpresa().equals(empresa.getIdEmpresa())) {
            throw new ResourceNotFound("Evaluación no encontrada");
        }

        return evaluacionMapper.toResponse(evaluacion);
    }

    @Transactional
    public EvaluacionResponse actualizarEvaluacion(Long id, EvaluacionRequest request) {
        Empresa empresa = getEmpresaAutenticada();
        EvaluacionFinanciera evaluacion = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Evaluación no encontrada"));

        if (!evaluacion.getEmpresa().getIdEmpresa().equals(empresa.getIdEmpresa())) {
            throw new ResourceNotFound("Evaluación no encontrada");
        }

        evaluacion.setNombrePersona(request.getNombreCliente());
        evaluacion.setDniCliente(request.getDniCliente());
        evaluacion.setSueldoMensual(request.getIngresosMensuales());
        evaluacion.setDeudasMensuales(request.getDeudasActuales());
        evaluacion.setEstadoSolicitud(request.getEstadoSolicitud());

        BigDecimal ingresos = request.getIngresosMensuales();
        BigDecimal deudas = request.getDeudasActuales();
        int score = calcularScore(ingresos, deudas);
        String riesgo = determinarRiesgo(score);

        evaluacion.setPuntajeScore(score);
        evaluacion.setResultadoRiesgo(riesgo);

        EvaluacionFinanciera actualizada = evaluacionRepository.save(evaluacion);
        return evaluacionMapper.toResponse(actualizada);
    }

    @Transactional
    public EvaluacionResponse actualizarEstado(Long id, CambioEstadoRequest request) {
        Empresa empresa = getEmpresaAutenticada();
        EvaluacionFinanciera evaluacion = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Evaluación no encontrada"));

        if (!evaluacion.getEmpresa().getIdEmpresa().equals(empresa.getIdEmpresa())) {
            throw new ResourceNotFound("Evaluación no encontrada");
        }

        evaluacion.setEstadoSolicitud(request.getEstadoSolicitud());
        EvaluacionFinanciera actualizada = evaluacionRepository.save(evaluacion);
        return evaluacionMapper.toResponse(actualizada);
    }

    @Transactional
    public void eliminar(Long id) {
        Empresa empresa = getEmpresaAutenticada();

        EvaluacionFinanciera evaluacion = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Evaluación no encontrada"));

        if (!evaluacion.getEmpresa().getIdEmpresa().equals(empresa.getIdEmpresa())) {
            throw new ResourceNotFound("Evaluación no encontrada");

        }
        evaluacionRepository.delete(evaluacion);
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