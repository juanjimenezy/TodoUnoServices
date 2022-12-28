package com.todouno.app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todouno.app.models.Inventario;
import com.todouno.app.services.InventarioService;

@RestController
@RequestMapping("/Inventario")
public class InventarioController {
	
	@Autowired
	private InventarioService inventarioService;
	
	@GetMapping("/productos")
	public ResponseEntity<?> inventario() {
		Map<String,Object> response = new HashMap<>();
		List<Inventario> inventario = new ArrayList<Inventario>();
		try {
			inventario = inventarioService.inventario();
			if(inventario.isEmpty()){
				response.put("error", "No existen productos.");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<Inventario>>(inventario,HttpStatus.OK);
	}

}
