package com.todouno.app;

import java.util.Arrays;
import java.util.List;

import com.todouno.app.models.Factura;
import com.todouno.app.models.Inventario;
import com.todouno.app.models.Producto;

public class Datos {

	public static final Producto PRODUCTO1 = new Producto(1L, "SPIDERMAN LEGO");
	public static final Producto PRODUCTO2 = new Producto(2L, "THOR LEGO");
	public static final List<Producto> LISTAPRODUCTOS = Arrays.asList(PRODUCTO1, PRODUCTO2);

	public static final Inventario INVENTARIO1 = new Inventario(1L, PRODUCTO1, 9);
	public static final Inventario INVENTARIO2 = new Inventario(2L, PRODUCTO1, 2);
	public static final List<Inventario> INVENTARIO = Arrays.asList(INVENTARIO1, INVENTARIO2);

	public static final Factura FACTURA1 = new Factura(1L, PRODUCTO1, 1, 2000.0, 2000.0);
	public static final Factura FACTURA2 = new Factura(2L, PRODUCTO2, 1, 2000.0, 2000.0);
	public static final List<Factura> LISTAFACTURAS = Arrays.asList(FACTURA1,FACTURA2);
	
}
