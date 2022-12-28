package com.todouno.app.interfaces;

import java.util.List;

import com.todouno.app.models.Factura;

public interface IFacturaService {

	public Factura facturaVenta(Factura factura);
	
	public List<Factura> facturas();
	
}
