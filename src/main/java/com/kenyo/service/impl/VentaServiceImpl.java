package com.kenyo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kenyo.model.DetalleVenta;
import com.kenyo.model.Venta;
import com.kenyo.repository.ProductoRepository;
import com.kenyo.repository.VentaRepository;
import com.kenyo.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService {

	
	@Autowired
	private VentaRepository repository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Transactional
	@Override
	public Venta registrar(Venta t) {
		t.setIdVenta(0L);
		double importe = 0;
		for (DetalleVenta d : t.getDetalleVenta()) {
			d.setVenta(t);
			d.setProducto(productoRepository.findOne(d.getProducto().getIdProducto()));
			d.setSubTotal(d.getCantidad() * d.getProducto().getPrecio());
			importe += d.getSubTotal(); 
		}
		t.setImporte(importe);
		t = repository.save(t);
		return repository.save(t);
	}

	@Override
	public Venta modificar(Venta t) {
		Venta Venta = repository.findOne(t.getIdVenta());
		if(Venta != null) {
			return repository.save(t);
		}
		return null;
	}

	@Override
	public boolean eliminar(Long id) {
		Venta Venta = repository.findOne(id);
		if(Venta != null) {
			repository.delete(id);
			return true;
		}
		return false;
		
	}

	@Override
	public Venta listarId(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<Venta> listar() {
		return repository.findAll();
	}

}
