package com.finrisk.controller;

import com.finrisk.dto.ProductoCreditoRequest;
import com.finrisk.dto.ProductoCreditoResponse;
import com.finrisk.service.ProductoCreditoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoCreditoController {

    private final ProductoCreditoService productoCreditoService;

    public ProductoCreditoController(ProductoCreditoService productoCreditoService) {
        this.productoCreditoService = productoCreditoService;
    }

    @PostMapping
    public ResponseEntity<ProductoCreditoResponse> crear(@Valid @RequestBody ProductoCreditoRequest request) {
        return new ResponseEntity<>(productoCreditoService.crear(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductoCreditoResponse>> listar() {
        return ResponseEntity.ok(productoCreditoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoCreditoResponse> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(productoCreditoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoCreditoResponse> actualizar(@PathVariable Integer id, @Valid @RequestBody ProductoCreditoRequest request) {
        return ResponseEntity.ok(productoCreditoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        productoCreditoService.eliminar(id);
        return ResponseEntity.ok("Producto eliminado exitosamente");
    }
}
