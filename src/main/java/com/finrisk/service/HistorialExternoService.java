package com.finrisk.service;

import com.finrisk.dto.HistorialExternoRequest;
import com.finrisk.dto.HistorialExternoResponse;
import com.finrisk.entity.HistorialExterno;
import com.finrisk.exception.ResourceNotFound;
import com.finrisk.mapper.HistorialExternoMapper;
import com.finrisk.repository.HistorialExternoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HistorialExternoService {

    private final HistorialExternoRepository historialExternoRepository;
    private final HistorialExternoMapper mapper;

    public HistorialExternoService(HistorialExternoRepository historialExternoRepository, HistorialExternoMapper mapper) {
        this.historialExternoRepository = historialExternoRepository;
        this.mapper = mapper;
    }

    @Transactional
    public HistorialExternoResponse crear(HistorialExternoRequest request) {
        if (historialExternoRepository.findByDni(request.getDni()).isPresent()) {
            throw new RuntimeException("Ya existe un registro con el DNI: " + request.getDni());
        }
        return mapper.toResponse(historialExternoRepository.save(mapper.toEntity(request)));
    }

    public List<HistorialExternoResponse> listarTodos() {
        return historialExternoRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public HistorialExternoResponse obtenerPorId(Integer id) {
        HistorialExterno historial = historialExternoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Historial externo no encontrado con id: " + id));
        return mapper.toResponse(historial);
    }

    public HistorialExternoResponse obtenerPorDni(String dni) {
        HistorialExterno historial = historialExternoRepository.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFound("No se encontró historial para el DNI: " + dni));
        return mapper.toResponse(historial);
    }

    @Transactional
    public HistorialExternoResponse actualizar(Integer id, HistorialExternoRequest request) {
        HistorialExterno historial = historialExternoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Historial externo no encontrado con id: " + id));

        historial.setDni(request.getDni());
        historial.setNombre(request.getNombre());
        historial.setApellido(request.getApellido());
        historial.setDeudaTotal(request.getDeudaTotal());
        historial.setNumeroEmpresas(request.getNumeroEmpresas());
        historial.setDiasMora(request.getDiasMora());

        return mapper.toResponse(historialExternoRepository.save(historial));
    }

    @Transactional
    public void eliminar(Integer id) {
        if (!historialExternoRepository.existsById(id)) {
            throw new ResourceNotFound("Historial externo no encontrado con id: " + id);
        }
        historialExternoRepository.deleteById(id);
    }
}
