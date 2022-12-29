package com.todouno.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todouno.app.interfaces.IInventarioService;
import com.todouno.app.models.Inventario;
import com.todouno.app.models.Kardex;
import com.todouno.app.models.Producto;
import com.todouno.app.repositories.InventarioRepository;

@Service
public class InventarioService implements IInventarioService{
	
	@Autowired
	private InventarioRepository inventarioRepository;

	@Override
	public Inventario entrada(Kardex kardex) {
		Inventario inventario = this.inventarioRepository.buscarPorProducto(kardex.getProducto().getId());
		Integer cantidad = inventario.getCantidad();
		cantidad = cantidad + kardex.getCantidad();
		inventario.setCantidad(cantidad);
		return this.inventarioRepository.save(inventario);
	}

	@Override
	public Inventario salida(Kardex kardex) {
		Inventario inventario = this.inventarioRepository.buscarPorProducto(kardex.getProducto().getId());
		Integer cantidad = inventario.getCantidad();
		if(kardex.getCantidad() > cantidad) {
			return null;
		}
		inventario.setCantidad(cantidad - kardex.getCantidad());
		return this.inventarioRepository.save(inventario);
	}

	@Override
	public Inventario init(Producto producto) {
		Inventario inventario = new Inventario();
		inventario.setProducto(producto);
		return this.inventarioRepository.save(inventario);
	}

	@Override
	public Integer cantidad(Producto producto) {
		Inventario inventario = this.inventarioRepository.buscarPorProducto(producto.getId());
		return inventario.getCantidad();
	}

	@Override
	public List<Inventario> inventario() {
		return (List<Inventario>) this.inventarioRepository.findAll();
	}
	
	

}
