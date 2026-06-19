package com.finrisk.service;

import com.finrisk.exception.BadCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.finrisk.dto.LoginRequest;
import com.finrisk.dto.RegistrarRequest;
import com.finrisk.entity.Empresa;
import com.finrisk.mapper.EmpresaMapper;
import com.finrisk.repository.EmpresaRepository;
import com.finrisk.security.JwtUtil;

@Service
public class EmpresaService {

	private final EmpresaRepository repo;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
    private final EmpresaMapper mapper;

    public EmpresaService(EmpresaRepository repo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, EmpresaMapper mapper) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
    }

    public String registrar(RegistrarRequest request) {
		
		if (repo.findByEmail(request.getEmail()).isPresent()) {
	        throw new RuntimeException("El email ya está registrado");
	    }
		
		Empresa empresa = (mapper.toEntity(request));

		empresa.setPassword(passwordEncoder.encode(request.getPassword()));
		repo.save(empresa);
		return "Empresa registrada exitosamente";
		
	}

    public String login(LoginRequest request) {
        Empresa empresa = repo.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Email o contraseña incorrectos"));

        if (!passwordEncoder.matches(request.getPassword(), empresa.getPassword())) {
            throw new BadCredentialsException("Email o contraseña incorrectos");
        }

        return jwtUtil.generateToken(empresa.getEmail());
    }
}
