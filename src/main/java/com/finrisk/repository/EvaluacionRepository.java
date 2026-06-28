package com.finrisk.repository;

import com.finrisk.entity.Evaluacion;
import com.finrisk.entity.Usuario;
import com.finrisk.dto.EvaluacionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer>, JpaSpecificationExecutor<Evaluacion> {
    List<Evaluacion> findByUsuario(Usuario usuario);
    List<EvaluacionProjection> findProjectedByUsuario(Usuario usuario);
}
