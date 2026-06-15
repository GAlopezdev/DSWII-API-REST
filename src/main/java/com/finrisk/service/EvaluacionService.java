package com.finrisk.service;

import com.finrisk.entity.EvaluacionFinanciera;
import com.finrisk.repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    // TAREA 3: Historial - Listar todas las evaluaciones
    public List<EvaluacionFinanciera> listarHistorial() {
        return evaluacionRepository.findAll();
    }

    // TAREA 3: Buscar una sola evaluación por ID
    public Optional<EvaluacionFinanciera> obtenerPorId(Integer id) {
        return evaluacionRepository.findById(id);
    }

    // TAREA 3: Limpieza - Eliminar un registro permanentemente (Hard-delete)
    public boolean eliminarEvaluacion(Integer id) {
        if (evaluacionRepository.existsById(id)) {
            evaluacionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}