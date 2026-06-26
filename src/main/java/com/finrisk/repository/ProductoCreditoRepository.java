package com.finrisk.repository;

import com.finrisk.entity.ProductoCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoCreditoRepository extends JpaRepository<ProductoCredito, Integer> {
}
