package com.kenyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kenyo.model.Persona;
import com.kenyo.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
