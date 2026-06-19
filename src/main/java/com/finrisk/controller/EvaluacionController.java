package com.finrisk.controller;

import com.finrisk.dto.EvaluacionRequest;
import com.finrisk.dto.EvaluacionResponse;
import com.finrisk.service.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {

	@Autowired
	private EvaluacionService evaluacionService;

	@PostMapping
	public ResponseEntity<EvaluacionResponse> registrarEvaluacion(@RequestBody EvaluacionRequest request) {
		EvaluacionResponse response = evaluacionService.crearEvaluacion(request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}