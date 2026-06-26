package com.finrisk.repository;

import com.finrisk.entity.HistorialExterno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistorialExternoRepository extends JpaRepository<HistorialExterno, Integer> {
    Optional<HistorialExterno> findByDni(String dni);
}
