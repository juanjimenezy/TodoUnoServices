package com.todouno.app.interfaces;

import java.util.List;

import com.todouno.app.models.Kardex;

public interface IKardexService {
	
	public List<Kardex> buscarTodos();
	
	public Kardex buscarPorProducto(Long idProducto);
	
	public Kardex entrada(Kardex kardex);
	
	public Kardex salida(Kardex kardex);

}
