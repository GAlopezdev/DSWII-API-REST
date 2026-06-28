package com.finrisk.controller;

import com.finrisk.dto.HistorialExternoRequest;
import com.finrisk.dto.HistorialExternoResponse;
import com.finrisk.service.HistorialExternoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.finrisk.dto.HistorialExternoSearchRequest;
import com.finrisk.dto.HistorialExternoProjection;
import com.finrisk.entity.HistorialExterno;

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

    @GetMapping("/find")
    public List<HistorialExterno> findHistorial(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido,
            @RequestParam(required = false) String dni) {
        
        HistorialExternoSearchRequest request = new HistorialExternoSearchRequest();
        request.setNombre(nombre);
        request.setApellido(apellido);
        request.setDni(dni);
        
        return historialExternoService.find(request);
    }

    @GetMapping("/projections")
    public ResponseEntity<List<HistorialExternoProjection>> listarProyecciones() {
        return ResponseEntity.ok(historialExternoService.getAllProjected());
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
