package com.empresa.repository;

import com.empresa.model.entity.EvaluacionFinanciera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRepository extends JpaRepository<EvaluacionFinanciera, Long> {
}