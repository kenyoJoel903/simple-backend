package com.kenyo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.kenyo.model.Venta;
import com.kenyo.service.VentaService;
import com.kenyo.service.VentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {
	
	@Autowired
	private VentaService service;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Venta>> listar(){
		List<Venta> lista = service.listar();
		if(lista == null) {
			lista = new ArrayList<>();
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public Resource<Venta> listarId(@PathVariable("id") Long id){
		Venta _venta = service.listarId(id);
		if(_venta == null) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		Resource<Venta> resource = new Resource<Venta>(_venta);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarId(id));
		resource.add(linkTo.withRel("all-ventas"));
		return resource;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Venta> createVenta(@Valid @RequestBody Venta venta ){
		Venta _venta  = service.registrar(venta);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(_venta.getIdVenta()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Venta> actualizarVenta(@Valid @RequestBody Venta venta ){
		Venta _venta  = service.modificar(venta);
		if(_venta == null ) {
			throw new ModeloNotFoundException("ID: " + _venta.getIdVenta());
		}
		return new ResponseEntity<>(_venta, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void eliminar(@PathVariable("id") Long id) {
		boolean resultado = service.eliminar(id);
		if(!resultado) {
			throw new ModeloNotFoundException("ID: " + id);
		}
	}
	
	

}
