package com.finrisk.controller;

import com.finrisk.entity.EvaluacionFinanciera;
import com.finrisk.service.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping
    public ResponseEntity<List<EvaluacionFinanciera>> listar() {
        List<EvaluacionFinanciera> historial = evaluacionService.listarHistorial();
        return ResponseEntity.ok(historial);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionFinanciera> buscarPorId(@PathVariable Integer id) {
        return evaluacionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        boolean eliminado = evaluacionService.eliminarEvaluacion(id);
        if (eliminado) {
            return ResponseEntity.ok("Borrado exitosamente");
        } else {
            return ResponseEntity.status(404).body("Error: La evaluación con ID " + id + " no existe.");
        }
    }
}