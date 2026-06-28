package com.finrisk.service;

import com.finrisk.dto.EvaluacionRequest;
import com.finrisk.dto.EvaluacionResponse;
import com.finrisk.entity.Evaluacion;
import com.finrisk.entity.HistorialExterno;
import com.finrisk.entity.ProductoCredito;
import com.finrisk.entity.Usuario;
import com.finrisk.exception.BadCredentialsException;
import com.finrisk.exception.ResourceNotFound;
import com.finrisk.mapper.EvaluacionMapper;
import com.finrisk.repository.EvaluacionRepository;
import com.finrisk.repository.HistorialExternoRepository;
import com.finrisk.repository.ProductoCreditoRepository;
import com.finrisk.repository.UsuarioRepository;
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
    private final HistorialExternoRepository historialExternoRepository;
    private final ProductoCreditoRepository productoCreditoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EvaluacionMapper mapper;

    public EvaluacionService(
            EvaluacionRepository evaluacionRepository,
            HistorialExternoRepository historialExternoRepository,
            ProductoCreditoRepository productoCreditoRepository,
            UsuarioRepository usuarioRepository,
            EvaluacionMapper mapper) {
        this.evaluacionRepository = evaluacionRepository;
        this.historialExternoRepository = historialExternoRepository;
        this.productoCreditoRepository = productoCreditoRepository;
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
    }

    @Transactional
    public EvaluacionResponse evaluar(EvaluacionRequest request) {
        Usuario asesor = getUsuarioAutenticado();

        HistorialExterno historial = historialExternoRepository.findByDni(request.getDni())
                .orElseThrow(() -> new ResourceNotFound(
                        "No se encontró historial externo para el DNI: " + request.getDni()));

        ProductoCredito producto = productoCreditoRepository.findById(request.getProductoId())
                .orElseThrow(() -> new ResourceNotFound(
                        "Producto de crédito no encontrado con id: " + request.getProductoId()));

        int scoreObtenido = calcularScore(historial);
        String estado = scoreObtenido >= producto.getScoreMinimo() ? "APROBADO" : "RECHAZADO";

        String comentarioAuto = generarComentario(historial, scoreObtenido, producto.getScoreMinimo(), estado);
        String comentarioFinal = (request.getComentarios() != null && !request.getComentarios().isBlank())
                ? request.getComentarios()
                : comentarioAuto;

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setHistorialExterno(historial);
        evaluacion.setProductoCredito(producto);
        evaluacion.setUsuario(asesor);
        evaluacion.setScoreObtenido(scoreObtenido);
        evaluacion.setEstado(estado);
        evaluacion.setComentarios(comentarioFinal);

        return mapper.toResponse(evaluacionRepository.save(evaluacion));
    }

    public List<EvaluacionResponse> listarMisEvaluaciones() {
        Usuario asesor = getUsuarioAutenticado();
        return evaluacionRepository.findByUsuario(asesor)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public EvaluacionResponse obtenerPorId(Integer id) {
        Evaluacion evaluacion = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Evaluación no encontrada con id: " + id));
        return mapper.toResponse(evaluacion);
    }

    private int calcularScore(HistorialExterno historial) {
        int scoreBase = 1000;

        // (deuda_total / 50)
        double penDeuda = historial.getDeudaTotal()
                .divide(new BigDecimal("50"), 4, RoundingMode.HALF_UP)
                .doubleValue();

        // (dias_mora * 2)
        int penMora = historial.getDiasMora() * 2;

        // (numero_empresas * 20)
        int penEmpresas = historial.getNumeroEmpresas() * 20;

        // ((sueldo - 1130) / 100)
        double bonoSueldo = historial.getSueldo()
                .subtract(new BigDecimal("1130"))
                .divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP)
                .doubleValue();

        int scoreFinal = scoreBase - (int) penDeuda - penMora - penEmpresas + (int) bonoSueldo;
        return Math.max(0, Math.min(1000, scoreFinal));
    }

    private String generarComentario(HistorialExterno historial, int score, int scoreMinimo, String estado) {
        return String.format(
                "Score obtenido: %d / Score mínimo requerido: %d. " +
                "Sueldo: S/%.2f, Deuda total: S/%.2f, Días de mora: %d, Empresas reportadas: %d. Estado: %s.",
                score, scoreMinimo,
                historial.getSueldo(),
                historial.getDeudaTotal(),
                historial.getDiasMora(),
                historial.getNumeroEmpresas(),
                estado);
    }

    private Usuario getUsuarioAutenticado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Usuario no encontrado"));
    }
}
