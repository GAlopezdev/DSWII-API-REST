package com.finrisk.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistrarRequest {
	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;
	
	@Email(message = "Debe ser un formato de email válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;
	
	@Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
    
    
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
