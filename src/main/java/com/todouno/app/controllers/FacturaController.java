package com.todouno.app.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todouno.app.dto.FacturaResponseDTO;
import com.todouno.app.interfaces.IFacturaService;
import com.todouno.app.models.Factura;

@RestController
@RequestMapping("/Factura")
public class FacturaController {
	
	@Autowired
	private IFacturaService facturaService;
	
	@GetMapping("/facturas")
	public ResponseEntity<FacturaResponseDTO> facturas() {
		FacturaResponseDTO response = new FacturaResponseDTO();
		List<Factura> facturas = new ArrayList<Factura>();
		try {
			facturas = this.facturaService.facturas();
			if (facturas.isEmpty()) {
				response.setMensaje("No existen facturas");
				return new ResponseEntity<FacturaResponseDTO>(response, HttpStatus.NOT_FOUND);
			}
			response.setFacturas(facturas);
		} catch (Exception e) {
			response.setError(true);
			response.setMensaje(e.getCause().toString());
			return new ResponseEntity<FacturaResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<FacturaResponseDTO>(response,HttpStatus.OK);
	}
	
	@PostMapping("/facturaVenta")
	public ResponseEntity<FacturaResponseDTO> facturaVenta(@RequestBody Factura factura) {
		FacturaResponseDTO response = new FacturaResponseDTO(); 
		Factura newFactura = null;
		try {
			newFactura = this.facturaService.facturaVenta(factura);
			if(newFactura == null) {
				response.setError(true);
				response.setMensaje("Error en salida de producto");
				return new ResponseEntity<FacturaResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			response.setError(true);
			response.setMensaje(e.getMessage());
			return new ResponseEntity<FacturaResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setFacturas(Arrays.asList(newFactura));
		return new ResponseEntity<FacturaResponseDTO>(response,HttpStatus.OK);
	}

}
