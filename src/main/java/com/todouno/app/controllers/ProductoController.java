package com.todouno.app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todouno.app.interfaces.IProductoService;
import com.todouno.app.models.Producto;

@RestController
@RequestMapping("/Producto")
public class ProductoController {
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/productos")
	public ResponseEntity<?> buscarTodos(){
		Map<String,Object> response = new HashMap<>();
		List<Producto> productos = new ArrayList<Producto>();
		try {
			productos = this.productoService.buscarTodos();
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Producto>>(productos,HttpStatus.OK);
	}

	
	@PostMapping("/producto")
	public ResponseEntity<?> crear(@RequestBody Producto producto) {
		Map<String,Object> response = new HashMap<>();
		Producto newProducto = null;
		try {
			newProducto = this.productoService.guardar(producto);
			if(newProducto == null) {
				response.put("error", "Error grabando producto");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("producto", newProducto);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}
