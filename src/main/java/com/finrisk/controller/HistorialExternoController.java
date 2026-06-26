package com.finrisk.controller;

import com.finrisk.dto.HistorialExternoRequest;
import com.finrisk.dto.HistorialExternoResponse;
import com.finrisk.service.HistorialExternoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial")
public class HistorialExternoController {

    private final HistorialExternoService historialExternoService;

    public HistorialExternoController(HistorialExternoService historialExternoService) {
        this.historialExternoService = historialExternoService;
    }

    @PostMapping
    public ResponseEntity<HistorialExternoResponse> crear(@Valid @RequestBody HistorialExternoRequest request) {
        return new ResponseEntity<>(historialExternoService.crear(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HistorialExternoResponse>> listar() {
        return ResponseEntity.ok(historialExternoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialExternoResponse> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(historialExternoService.obtenerPorId(id));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<HistorialExternoResponse> obtenerPorDni(@PathVariable String dni) {
        return ResponseEntity.ok(historialExternoService.obtenerPorDni(dni));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialExternoResponse> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody HistorialExternoRequest request) {
        return ResponseEntity.ok(historialExternoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        historialExternoService.eliminar(id);
        return ResponseEntity.ok("Historial eliminado exitosamente");
    }
}
