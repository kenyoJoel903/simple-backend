package com.kenyo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenyo.model.Persona;
import com.kenyo.repository.PersonaRepository;
import com.kenyo.service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService {

	
	@Autowired
	private PersonaRepository repository;
	
	@Override
	public Persona registrar(Persona t) {
		t.setIdPersona(0L);
		return repository.save(t);
	}

	@Override
	public Persona modificar(Persona t) {
		Persona persona = repository.findOne(t.getIdPersona());
		if(persona != null) {
			return repository.save(t);
		}
		return null;
	}

	@Override
	public boolean eliminar(Long id) {
		Persona persona = repository.findOne(id);
		if(persona != null) {
			repository.delete(id);
			return true;
		}
		return false;
		
	}

	@Override
	public Persona listarId(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<Persona> listar() {
		return repository.findAll();
	}

}
