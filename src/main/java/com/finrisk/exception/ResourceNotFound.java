package com.finrisk.exception;

public class ResourceNotFound extends RuntimeException {

	private static final long serialVersionUID = 6564749757174184526L;

	public ResourceNotFound(String message) {
		super(message);
	}
	
}
