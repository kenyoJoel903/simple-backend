package com.kenyo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kenyo.model.Producto;
import com.kenyo.repository.ProductoRepository;
import com.kenyo.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	
	@Autowired
	private ProductoRepository repository;
	
	@Override
	public Producto registrar(Producto t) {
		t.setIdProducto(0L);
		return repository.save(t);
	}

	@Override
	public Producto modificar(Producto t) {
		Producto Producto = repository.findOne(t.getIdProducto());
		if(Producto != null) {
			return repository.save(t);
		}
		return null;
	}

	@Override
	public boolean eliminar(Long id) {
		Producto Producto = repository.findOne(id);
		if(Producto != null) {
			repository.delete(id);
			return true;
		}
		return false;
		
	}

	@Override
	public Producto listarId(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<Producto> listar() {
		return repository.findAll();
	}

	@Override
	public Page<Producto> listarPageable(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	

}
