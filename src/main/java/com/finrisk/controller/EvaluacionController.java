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

    private final EvaluacionService service;

    public EvaluacionController(EvaluacionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EvaluacionResponse> registrar(@Valid @RequestBody EvaluacionRequest req) {
        return new ResponseEntity<>(service.crearEvaluacion(req), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EvaluacionResponse>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluacionResponse> actualizar(@PathVariable Long id, @Valid @RequestBody EvaluacionRequest req) {
        return ResponseEntity.ok(service.actualizarEvaluacion(id, req));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<EvaluacionResponse> cambiarEstado(@PathVariable Long id, @Valid @RequestBody CambioEstadoRequest req) {
        return ResponseEntity.ok(service.actualizarEstado(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}