package com.jpa.andres.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jpa.andres.model.Producto;
import com.jpa.andres.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public long countProductos() {
        return productoRepository.count();
    }
}
