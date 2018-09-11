package com.kenyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kenyo.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

}
