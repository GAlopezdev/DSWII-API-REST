package com.finrisk.security;

import java.util.Date;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;


@Component
public class JwtUtil {
	private static final String SECRET_STRING = "mi_clave_super_secreta_de_256_bytes!!";
	private SecretKey key;

	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));
	}

	public String generateToken(String username) {
		return Jwts.builder().setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 300000))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}

	public Claims getClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key).build()
				.parseClaimsJws(token).getBody();
	}

	public boolean validateToken(String token, String username) {
		try {
			return getClaims(token).getSubject().equals(username);
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}
}
