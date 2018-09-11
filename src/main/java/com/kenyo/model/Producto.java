package com.kenyo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Producto {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProducto;
	
	
	@Size(min = 2, message = "El nombre deben de tener mínimo 2 caracteres")
	@Column(name="nombres", nullable=false, length=50)
	private String nombre;
	
	
	
	@Size(min = 2, message = "La marca debe de tener mínimo 2 caracteres")
	@Column(name="marca", nullable=false, length=50)
	private String marca;
	
	@Column(precision = 5, scale = 2)
	private double precio;



	public Long getIdProducto() {
		return idProducto;
	}



	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getMarca() {
		return marca;
	}



	public void setMarca(String marca) {
		this.marca = marca;
	}



	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
	

}
