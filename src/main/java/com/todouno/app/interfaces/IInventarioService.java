package com.todouno.app.interfaces;

import java.util.List;

import com.todouno.app.models.Inventario;
import com.todouno.app.models.Kardex;
import com.todouno.app.models.Producto;

public interface IInventarioService {
	
	public List<Inventario> inventario();
	
	public Inventario entrada(Kardex kardex);
	
	public Inventario salida(Kardex kardex);

	public Inventario init(Producto producto);
	
	public Integer cantidad(Producto producto);
}
