package com.finrisk.repository;

import com.finrisk.entity.HistorialExterno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.finrisk.dto.HistorialExternoProjection;
import java.util.List;

import java.util.Optional;

@Repository
public interface HistorialExternoRepository extends JpaRepository<HistorialExterno, Integer>, JpaSpecificationExecutor<HistorialExterno> {
    Optional<HistorialExterno> findByDni(String dni);
    List<HistorialExternoProjection> findAllProjectedBy();
}
