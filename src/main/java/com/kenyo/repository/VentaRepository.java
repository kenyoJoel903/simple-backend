package com.kenyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kenyo.model.Persona;
import com.kenyo.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {

}
