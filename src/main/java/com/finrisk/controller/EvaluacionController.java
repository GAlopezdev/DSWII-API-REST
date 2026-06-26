package com.finrisk.controller;

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
    public ResponseEntity<EvaluacionResponse> evaluar(@Valid @RequestBody EvaluacionRequest request) {
        return new ResponseEntity<>(evaluacionService.evaluar(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EvaluacionResponse>> listarMias() {
        return ResponseEntity.ok(evaluacionService.listarMisEvaluaciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionResponse> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(evaluacionService.obtenerPorId(id));
    }
}
