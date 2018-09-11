package com.kenyo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Persona {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersona;
	
	
	@Size(min = 2, message = "Los nombres deben de tener mínimo 2 caracteres")
	@Column(name="nombres", nullable=false, length=50)
	private String nombres;
	
	
	@Size(min = 2, message = "Los apellidos deben de tener mínimo 2 caracteres")
	@Column(name="apellidos", nullable=false, length=50)
	private String apellidos;

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	

}
