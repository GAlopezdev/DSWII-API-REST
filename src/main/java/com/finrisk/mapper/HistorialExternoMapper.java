package com.finrisk.mapper;

import com.finrisk.dto.HistorialExternoRequest;
import com.finrisk.dto.HistorialExternoResponse;
import com.finrisk.entity.HistorialExterno;
import org.springframework.stereotype.Component;

@Component
public class HistorialExternoMapper {

    public HistorialExterno toEntity(HistorialExternoRequest request) {
        if (request == null) return null;
        HistorialExterno historial = new HistorialExterno();
        historial.setDni(request.getDni());
        historial.setNombre(request.getNombre());
        historial.setApellido(request.getApellido());
        historial.setDeudaTotal(request.getDeudaTotal());
        historial.setNumeroEmpresas(request.getNumeroEmpresas());
        historial.setDiasMora(request.getDiasMora());
        historial.setSueldo(request.getSueldo());
        return historial;
    }

    public HistorialExternoResponse toResponse(HistorialExterno historial) {
        if (historial == null) return null;
        HistorialExternoResponse response = new HistorialExternoResponse();
        response.setExternoId(historial.getExternoId());
        response.setDni(historial.getDni());
        response.setNombre(historial.getNombre());
        response.setApellido(historial.getApellido());
        response.setDeudaTotal(historial.getDeudaTotal());
        response.setNumeroEmpresas(historial.getNumeroEmpresas());
        response.setDiasMora(historial.getDiasMora());
        response.setSueldo(historial.getSueldo());
        return response;
    }
}
