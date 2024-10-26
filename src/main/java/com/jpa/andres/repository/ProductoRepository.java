package com.jpa.andres.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.andres.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    //Método adicional para contar el número de productos
    long count();
   }
