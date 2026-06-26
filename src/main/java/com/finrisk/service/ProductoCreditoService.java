package com.finrisk.service;

import com.finrisk.dto.ProductoCreditoRequest;
import com.finrisk.dto.ProductoCreditoResponse;
import com.finrisk.entity.ProductoCredito;
import com.finrisk.exception.ResourceNotFound;
import com.finrisk.mapper.ProductoCreditoMapper;
import com.finrisk.repository.ProductoCreditoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoCreditoService {

    private final ProductoCreditoRepository productoCreditoRepository;
    private final ProductoCreditoMapper mapper;

    public ProductoCreditoService(ProductoCreditoRepository productoCreditoRepository, ProductoCreditoMapper mapper) {
        this.productoCreditoRepository = productoCreditoRepository;
        this.mapper = mapper;
    }

    @Transactional
    public ProductoCreditoResponse crear(ProductoCreditoRequest request) {
        ProductoCredito producto = mapper.toEntity(request);
        return mapper.toResponse(productoCreditoRepository.save(producto));
    }

    public List<ProductoCreditoResponse> listarTodos() {
        return productoCreditoRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ProductoCreditoResponse obtenerPorId(Integer id) {
        ProductoCredito producto = productoCreditoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Producto de crédito no encontrado con id: " + id));
        return mapper.toResponse(producto);
    }

    @Transactional
    public ProductoCreditoResponse actualizar(Integer id, ProductoCreditoRequest request) {
        ProductoCredito producto = productoCreditoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Producto de crédito no encontrado con id: " + id));

        producto.setNombreProducto(request.getNombreProducto());
        producto.setMontoMinimo(request.getMontoMinimo());
        producto.setMontoMaximo(request.getMontoMaximo());
        producto.setTasaInteres(request.getTasaInteres());
        producto.setScoreMinimo(request.getScoreMinimo());

        return mapper.toResponse(productoCreditoRepository.save(producto));
    }

    @Transactional
    public void eliminar(Integer id) {
        if (!productoCreditoRepository.existsById(id)) {
            throw new ResourceNotFound("Producto de crédito no encontrado con id: " + id);
        }
        productoCreditoRepository.deleteById(id);
    }
}
