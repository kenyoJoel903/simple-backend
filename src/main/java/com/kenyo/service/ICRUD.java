package com.kenyo.service;

import java.util.List;

public interface ICRUD<T> {
	
	T registrar(T t);
	
	T modificar(T t);

	boolean eliminar(Long id);

	T listarId(Long id);

	List<T> listar();

}
