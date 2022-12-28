package com.todouno.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todouno.app.interfaces.IFacturaService;
import com.todouno.app.interfaces.IInventarioService;
import com.todouno.app.interfaces.IKardexService;
import com.todouno.app.interfaces.IProductoService;
import com.todouno.app.models.Factura;
import com.todouno.app.models.Inventario;
import com.todouno.app.models.Kardex;
import com.todouno.app.models.Producto;
import com.todouno.app.repositories.FacturaRepository;

@Service
public class FacturaService implements IFacturaService{
	
	private static String TIPO_OPERACION = "FACTURA_VENTA";
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IKardexService kardexService;
	
	@Autowired
	private IInventarioService inventarioService;
	
	@Autowired
	private FacturaRepository facturaRepository;

	@Override
	public Factura facturaVenta(Factura factura) {
		Kardex kardex = new Kardex();
		Producto producto = this.productoService.buscarPorId(factura.getProducto().getId());
		kardex.setDescripcion(TIPO_OPERACION);
		kardex.setProducto(producto);
		kardex.setCantidad(factura.getCantidad());
		kardex.setValor(factura.getValorUnitario() * factura.getCantidad());
		kardex = this.kardexService.salida(kardex);
		if(kardex == null){
			return null;
		}
		Inventario inventario = this.inventarioService.salida(kardex);
		if(inventario  == null) {
			return null;
		}
		Factura newFactura = new Factura();
		newFactura.setProducto(producto);
		newFactura.setCantidad(factura.getCantidad());
		newFactura.setValorUnitario(factura.getValorUnitario());
		newFactura.setValorTotal(factura.getValorUnitario() * factura.getCantidad());
		
		return this.facturaRepository.save(newFactura);
	}

	@Override
	public List<Factura> facturas() {
		return (List<Factura>) this.facturaRepository.findAll();
	}

}
