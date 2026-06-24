package com.finrisk.repository;

import com.finrisk.entity.Empresa;
import com.finrisk.entity.EvaluacionFinanciera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EvaluacionRepository extends JpaRepository<EvaluacionFinanciera, Long> {
    List<EvaluacionFinanciera> findByEmpresa(Empresa empresa);
}