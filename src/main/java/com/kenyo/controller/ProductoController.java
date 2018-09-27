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
import com.kenyo.model.Producto;
import com.kenyo.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	private ProductoService service;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> listar(){
		List<Producto> lista = service.listar();
		if(lista == null) {
			lista = new ArrayList<>();
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping(value="/pageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Producto>> listarPageable(Pageable pageable){
		Page<Producto> productos;
		productos = service.listarPageable(pageable);
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public Resource<Producto> listarId(@PathVariable("id") Long id){
		Producto _producto = service.listarId(id);
		if(_producto == null) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		Resource<Producto> resource = new Resource<Producto>(_producto);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarId(id));
		resource.add(linkTo.withRel("all-productos"));
		return resource;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> createProducto(@Valid @RequestBody Producto producto ){
		Producto _producto  = service.registrar(producto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(_producto.getIdProducto()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> actualizarProducto(@Valid @RequestBody Producto producto ){
		Producto _producto  = service.modificar(producto);
		if(_producto == null ) {
			throw new ModeloNotFoundException("ID: " + _producto.getIdProducto());
		}
		return new ResponseEntity<>(_producto, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void eliminar(@PathVariable("id") Long id) {
		boolean resultado = service.eliminar(id);
		if(!resultado) {
			throw new ModeloNotFoundException("ID: " + id);
		}
	}
	
	

}
