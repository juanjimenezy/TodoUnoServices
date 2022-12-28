package com.todouno.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todouno.app.enums.TipoOperacionKardex;
import com.todouno.app.interfaces.IInventarioService;
import com.todouno.app.interfaces.IKardexService;
import com.todouno.app.interfaces.IProductoService;
import com.todouno.app.models.Kardex;
import com.todouno.app.models.Producto;
import com.todouno.app.repositories.KardexRepository;

@Service
public class KardexService implements IKardexService{
	
	@Autowired
	private KardexRepository kardexRepository;
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IInventarioService inventarioService;

	@Override
	public List<Kardex> buscarTodos() {
		return (List<Kardex>) this.kardexRepository.findAll();
	}

	@Override
	public Kardex buscarPorProducto(Long idProducto) {
		return this.kardexRepository.buscarPorProducto(idProducto);
	}

	@Override
	public Kardex entrada(Kardex kardex) {
		kardex.setTipoOperacion(TipoOperacionKardex.IN);
		Producto producto = this.productoService.buscarPorId(kardex.getProducto().getId());
		kardex.setProducto(producto);
		return this.kardexRepository.save(kardex);
	}

	@Override
	public Kardex salida(Kardex kardex) {
		kardex.setTipoOperacion(TipoOperacionKardex.OUT);
		if (!verificarDisponibilidad(kardex.getProducto(),kardex.getCantidad())) {
			return null;
		}
		Producto producto = this.productoService.buscarPorId(kardex.getProducto().getId());
		kardex.setProducto(producto);
		return this.kardexRepository.save(kardex);
	}
	
	private boolean verificarDisponibilidad(Producto producto, Integer cantidad) {
		Integer cantInv = this.inventarioService.cantidad(producto);
		if(cantInv >= cantidad) {
			return true;
		}
		return false;
	}

}
