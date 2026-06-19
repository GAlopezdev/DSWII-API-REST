package com.finrisk.mapper;

import com.finrisk.dto.RegistrarRequest;
import com.finrisk.entity.Empresa;
import org.springframework.stereotype.Component;

@Component
public class EmpresaMapper {

    public Empresa toEntity(RegistrarRequest request) {
        Empresa empresa = new Empresa();
        
        empresa.setNombreEmpresa(request.getNombre());
        empresa.setEmail(request.getEmail());
                
        return empresa;
    }
}