package com.kenyo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DetalleVenta {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDetalleVenta;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_venta", nullable = false)
	private Venta venta;
	
	
	@ManyToOne
	@JoinColumn(name = "id_producto", nullable = false)
	private Producto producto;
	
	
	private short cantidad;
	
	
	@Column(precision = 5, scale = 2)
	private double subTotal;


	public Long getIdDetalleVenta() {
		return idDetalleVenta;
	}


	public void setIdDetalleVenta(Long idDetalleVenta) {
		this.idDetalleVenta = idDetalleVenta;
	}


	public Venta getVenta() {
		return venta;
	}


	public void setVenta(Venta venta) {
		this.venta = venta;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public short getCantidad() {
		return cantidad;
	}


	public void setCantidad(short cantidad) {
		this.cantidad = cantidad;
	}


	public double getSubTotal() {
		return subTotal;
	}


	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

}
