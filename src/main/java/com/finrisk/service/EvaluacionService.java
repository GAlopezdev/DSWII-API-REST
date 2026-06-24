package com.finrisk.service;

import com.finrisk.dto.CambioEstadoRequest;
import com.finrisk.dto.EvaluacionRequest;
import com.finrisk.dto.EvaluacionResponse;
import com.finrisk.entity.Empresa;
import com.finrisk.entity.EvaluacionFinanciera;
import com.finrisk.exception.BadCredentialsException;
import com.finrisk.exception.ResourceNotFound;
import com.finrisk.mapper.EvaluacionMapper;
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
        return empresaRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new BadCredentialsException("Empresa no encontrada"));
    }

    public EvaluacionResponse crearEvaluacion(EvaluacionRequest request) {
        EvaluacionFinanciera evaluacion = evaluacionMapper.toEntity(request);
        
        int score = calcularScore(request.getIngresosMensuales(), request.getDeudasActuales());
        evaluacion.setPuntajeScore(score);
        evaluacion.setResultadoRiesgo(determinarRiesgo(score));
        evaluacion.setEstadoSolicitud("PENDIENTE");
        evaluacion.setEmpresa(getEmpresaAutenticada());

        return evaluacionMapper.toResponse(evaluacionRepository.save(evaluacion));
    }

    public List<EvaluacionResponse> listarTodas() {
        return evaluacionRepository.findByEmpresa(getEmpresaAutenticada())
                .stream().map(evaluacionMapper::toResponse).toList();
    }

    public EvaluacionResponse obtenerPorId(Long id) {
        EvaluacionFinanciera eval = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("No encontrada"));
        if (!eval.getEmpresa().getIdEmpresa().equals(getEmpresaAutenticada().getIdEmpresa())) 
            throw new ResourceNotFound("Acceso denegado");
        return evaluacionMapper.toResponse(eval);
    }

    @Transactional
    public EvaluacionResponse actualizarEvaluacion(Long id, EvaluacionRequest request) {
        EvaluacionFinanciera eval = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("No encontrada"));
        
        if (!eval.getEmpresa().getIdEmpresa().equals(getEmpresaAutenticada().getIdEmpresa()))
            throw new ResourceNotFound("Acceso denegado");
        
        eval.setNombrePersona(request.getNombreCliente());
        eval.setDniCliente(request.getDniCliente());
        eval.setSueldoMensual(request.getIngresosMensuales());
        eval.setDeudasMensuales(request.getDeudasActuales());
        
        int score = calcularScore(request.getIngresosMensuales(), request.getDeudasActuales());
        eval.setPuntajeScore(score);
        eval.setResultadoRiesgo(determinarRiesgo(score));

        return evaluacionMapper.toResponse(evaluacionRepository.save(eval));
    }

    @Transactional
    public EvaluacionResponse actualizarEstado(Long id, CambioEstadoRequest request) {
        EvaluacionFinanciera eval = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("No encontrada"));
        
        if (!eval.getEmpresa().getIdEmpresa().equals(getEmpresaAutenticada().getIdEmpresa()))
            throw new ResourceNotFound("Acceso denegado");
            
        eval.setEstadoSolicitud(request.getEstadoSolicitud());
        return evaluacionMapper.toResponse(evaluacionRepository.save(eval));
    }

    @Transactional
    public void eliminar(Long id) {
        EvaluacionFinanciera eval = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("No encontrada"));
        
        if (!eval.getEmpresa().getIdEmpresa().equals(getEmpresaAutenticada().getIdEmpresa())) {
            throw new ResourceNotFound("Acceso denegado");
        }
        
        evaluacionRepository.delete(eval);
    }

    private int calcularScore(BigDecimal ingresos, BigDecimal deudas) {
        if (ingresos.compareTo(BigDecimal.ZERO) == 0 || deudas.compareTo(ingresos) >= 0) return 0;
        return ingresos.subtract(deudas).divide(ingresos, 4, RoundingMode.HALF_UP)
                .multiply(new BigDecimal("1000")).setScale(0, RoundingMode.HALF_UP).intValue();
    }

    private String determinarRiesgo(int score) {
        if (score >= 700) return "BAJO";
        if (score >= 300) return "MEDIO";
        return "ALTO";
    }
}