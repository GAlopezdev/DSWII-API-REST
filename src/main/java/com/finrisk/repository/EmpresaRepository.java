package com.finrisk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finrisk.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
	Optional<Empresa> findByEmail(String email);

}
