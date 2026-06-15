package com.empresa.controller;

import com.empresa.model.dto.EvaluacionRequest;
import com.empresa.model.dto.EvaluacionResponse;
import com.empresa.service.EvaluacionService;
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