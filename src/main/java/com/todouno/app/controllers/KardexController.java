package com.todouno.app.controllers;

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

import com.todouno.app.interfaces.IInventarioService;
import com.todouno.app.interfaces.IKardexService;
import com.todouno.app.models.Kardex;

@RestController
@RequestMapping("/Kardex")
public class KardexController {
	
	private static String TIPO_ENTRADA = "ENTRADA_X_ALMACEN";
	private static String TIPO_SALIDA = "SALIDA_X_ALMACEN";

	@Autowired
	private IKardexService kardexSerice;
	
	@Autowired
	private IInventarioService inventarioSerivce;
	
	@GetMapping("/kardex")
	public ResponseEntity<?> kardex() {
		Map<String,Object> response = new HashMap<>(); 
		List<Kardex> kardex = null;
		try {
			kardex = this.kardexSerice.buscarTodos();
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Kardex>>(kardex,HttpStatus.OK);
	}

	@PostMapping("/ingreso")
	public ResponseEntity<?> ingreso(@RequestBody Kardex kardex) {
		Map<String,Object> response = new HashMap<>(); 
		Kardex newKardex = null;
		try {
			kardex.setDescripcion(TIPO_ENTRADA);
			newKardex = this.kardexSerice.entrada(kardex);
			if(newKardex == null) {
				response.put("error", "Error al guardar entrada producto");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			this.inventarioSerivce.entrada(newKardex);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("Kardex", newKardex);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/salida")
	public ResponseEntity<?> salida(@RequestBody Kardex kardex) {
		Map<String,Object> response = new HashMap<>();
		Kardex newKardex = null;
		try {
			kardex.setDescripcion(TIPO_SALIDA);
			newKardex = this.kardexSerice.salida(kardex);
			if(newKardex == null) {
				response.put("error", "Error al guardar salida producto");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			this.inventarioSerivce.salida(newKardex);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("Kardex", newKardex);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}

}
