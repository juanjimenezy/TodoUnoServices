package com.todouno.app.interfaces;

import java.util.List;

import com.todouno.app.models.Producto;

public interface IProductoService {
	
	public List<Producto> buscarTodos();
	
	public Producto buscarPorId(Long id);
	
	public Producto guardar(Producto producto);

}
