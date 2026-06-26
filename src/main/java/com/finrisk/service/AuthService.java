package com.finrisk.service;

import com.finrisk.dto.LoginRequest;
import com.finrisk.dto.RegistrarRequest;
import com.finrisk.entity.Usuario;
import com.finrisk.exception.BadCredentialsException;
import com.finrisk.repository.UsuarioRepository;
import com.finrisk.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String registrar(RegistrarRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(request.getNombreCompleto());
        usuario.setEmail(request.getEmail());
        usuario.setContrasenia(passwordEncoder.encode(request.getContrasenia()));
        usuario.setRol("ASESOR");

        usuarioRepository.save(usuario);
        return "Usuario registrado exitosamente";
    }

    public String login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Email o contraseña incorrectos"));

        if (!passwordEncoder.matches(request.getContrasenia(), usuario.getContrasenia())) {
            throw new BadCredentialsException("Email o contraseña incorrectos");
        }

        return jwtUtil.generateToken(usuario.getEmail(), usuario.getRol());
    }
}
