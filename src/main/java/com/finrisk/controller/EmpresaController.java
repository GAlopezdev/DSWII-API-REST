package com.finrisk.controller;

import com.finrisk.dto.LoginRequest;
import com.finrisk.dto.LoginResponse;
import com.finrisk.dto.RegistrarRequest;
import com.finrisk.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;
    
    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@Valid @RequestBody RegistrarRequest request) {
        String mensaje = empresaService.registrar(request);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        String token = empresaService.login(request);
        return ResponseEntity.ok(new LoginResponse(token));
    }
}