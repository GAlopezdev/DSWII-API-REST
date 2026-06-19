package com.finrisk.repository;

import com.finrisk.entity.EvaluacionFinanciera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRepository extends JpaRepository<EvaluacionFinanciera, Long> {
}