package com.finrisk.repository;

import com.finrisk.entity.Evaluacion;
import com.finrisk.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer> {
    List<Evaluacion> findByUsuario(Usuario usuario);
}
