package com.finrisk.mapper;

import com.finrisk.dto.ProductoCreditoRequest;
import com.finrisk.dto.ProductoCreditoResponse;
import com.finrisk.entity.ProductoCredito;
import org.springframework.stereotype.Component;

@Component
public class ProductoCreditoMapper {

    public ProductoCredito toEntity(ProductoCreditoRequest request) {
        if (request == null) return null;
        ProductoCredito producto = new ProductoCredito();
        producto.setNombreProducto(request.getNombreProducto());
        producto.setMontoMinimo(request.getMontoMinimo());
        producto.setMontoMaximo(request.getMontoMaximo());
        producto.setTasaInteres(request.getTasaInteres());
        producto.setScoreMinimo(request.getScoreMinimo());
        return producto;
    }

    public ProductoCreditoResponse toResponse(ProductoCredito producto) {
        if (producto == null) return null;
        ProductoCreditoResponse response = new ProductoCreditoResponse();
        response.setProductoId(producto.getProductoId());
        response.setNombreProducto(producto.getNombreProducto());
        response.setMontoMinimo(producto.getMontoMinimo());
        response.setMontoMaximo(producto.getMontoMaximo());
        response.setTasaInteres(producto.getTasaInteres());
        response.setScoreMinimo(producto.getScoreMinimo());
        return response;
    }
}
