package com.finrisk.dto;

import org.springframework.beans.factory.annotation.Value;
import java.math.BigDecimal;

public interface HistorialExternoProjection {

    String getDni();
    
    String getNombre();
    
    String getApellido();
    
    @Value("#{target.nombre + ' ' + target.apellido}")
    String getNombreCompleto();
    
    BigDecimal getSueldo();
    
    BigDecimal getDeudaTotal();
    
    Integer getDiasMora();
    
    Integer getNumeroEmpresas();
}
