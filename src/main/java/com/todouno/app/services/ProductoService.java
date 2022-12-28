package com.todouno.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todouno.app.interfaces.IInventarioService;
import com.todouno.app.interfaces.IProductoService;
import com.todouno.app.models.Producto;
import com.todouno.app.repositories.ProductoRepository;

@Service
public class ProductoService implements IProductoService{
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private IInventarioService inventarioService;

	@Override
	public List<Producto> buscarTodos() {
		return (List<Producto>) this.productoRepository.findAll();
	}

	@Override
	public Producto buscarPorId(Long id) {
		return this.productoRepository.findById(id).orElse(null);
	}

	@Override
	public Producto guardar(Producto producto) {
		Producto newProducto = this.productoRepository.save(producto);
		this.inventarioService.init(newProducto);
		return newProducto;
	}

}
