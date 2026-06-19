package com.finrisk.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.finrisk.dto.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<String>> handledValidation
		(MethodArgumentNotValidException ex, HttpServletRequest request) {
		
		List<String> details = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).toList();

		ApiResponse<String> response = new ApiResponse<>();
		response.setSuccess(false);
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
		response.setMessage("Error de validación");
		response.setDetails(details);
		response.setPath(request.getRequestURI());
		response.setTimestamp(LocalDateTime.now());
		
		return ResponseEntity.badRequest().body(response);
	}
	
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ApiResponse<String>> handledNotFound
		(ResourceNotFound ex, HttpServletRequest request) {
		
		ApiResponse<String> response = new ApiResponse<>();
		response.setSuccess(false);
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
		response.setMessage(ex.getMessage());
		response.setPath(request.getRequestURI());
		response.setTimestamp(LocalDateTime.now());
		
		return ResponseEntity.badRequest().body(response);
	}

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<String>> handleBadCredentials(BadCredentialsException ex, HttpServletRequest request) {
        ApiResponse<String> response = new ApiResponse<>() ;
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<String>> handledNotFound
		(Exception ex, HttpServletRequest request) {
		
		ApiResponse<String> response = new ApiResponse<>();
		response.setSuccess(false);
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		response.setMessage(ex.getMessage());
		response.setPath(request.getRequestURI());
		response.setTimestamp(LocalDateTime.now());
		
		return ResponseEntity.badRequest().body(response);
	}

	
	
}
