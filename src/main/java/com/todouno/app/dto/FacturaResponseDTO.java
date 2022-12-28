package com.todouno.app.dto;

import java.util.List;

import com.todouno.app.models.Factura;

public class FacturaResponseDTO {
	
	private List<Factura> facturas;
	
	private Boolean error;
	
	private String mensaje;

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
