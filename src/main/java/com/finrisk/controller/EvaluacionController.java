package com.finrisk.controller;

import com.finrisk.dto.CambioEstadoRequest;
import com.finrisk.dto.EvaluacionRequest;
import com.finrisk.dto.EvaluacionResponse;
import com.finrisk.service.EvaluacionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    public EvaluacionController(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    @PostMapping
    public ResponseEntity<EvaluacionResponse> registrarEvaluacion(@Valid @RequestBody EvaluacionRequest request) {
        EvaluacionResponse response = evaluacionService.crearEvaluacion(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EvaluacionResponse>> listar() {
        List<EvaluacionResponse> lista = evaluacionService.listarTodas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionResponse> obtener(@PathVariable("id") Long id) {
        EvaluacionResponse response = evaluacionService.obtenerPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluacionResponse> actualizar(@PathVariable("id") Long id, @Valid @RequestBody EvaluacionRequest request) {
        EvaluacionResponse response = evaluacionService.actualizarEvaluacion(id, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<EvaluacionResponse> cambiarEstado(@PathVariable("id") Long id, @Valid @RequestBody CambioEstadoRequest request) {
        EvaluacionResponse response = evaluacionService.actualizarEstado(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        evaluacionService.eliminar(id);
        return ResponseEntity.ok("Borrado exitosamente");
    }
}