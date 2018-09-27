package com.kenyo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kenyo.exception.ModeloNotFoundException;
import com.kenyo.model.Persona;
import com.kenyo.service.PersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {
	
	@Autowired
	private PersonaService service;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Persona>> listar(){
		List<Persona> lista = service.listar();
		if(lista == null) {
			lista = new ArrayList<>();
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public Resource<Persona> listarId(@PathVariable("id") Long id){
		Persona _persona = service.listarId(id);
		if(_persona == null) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		Resource<Persona> resource = new Resource<Persona>(_persona);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarId(id));
		resource.add(linkTo.withRel("all-personas"));
		return resource;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Persona> createPersona(@Valid @RequestBody Persona persona ){
		Persona _persona  = service.registrar(persona);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(_persona.getIdPersona()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Persona> actualizarPersona(@Valid @RequestBody Persona persona ){
		Persona _persona  = service.modificar(persona);
		if(_persona == null ) {
			throw new ModeloNotFoundException("ID: " + _persona.getIdPersona());
		}
		return new ResponseEntity<>(_persona, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void eliminar(@PathVariable("id") Long id) {
		boolean resultado = service.eliminar(id);
		if(!resultado) {
			throw new ModeloNotFoundException("ID: " + id);
		}
	}
	
	@GetMapping(value="/pageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Persona>> pageable(Pageable pageable){
		Page<Persona> personas;
		personas = service.listarPageable(pageable);
		return new ResponseEntity<>(personas, HttpStatus.OK);
	}

	
	

}
