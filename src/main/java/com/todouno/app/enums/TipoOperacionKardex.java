package com.todouno.app.enums;

public enum TipoOperacionKardex {

	IN("Entrada"),OUT("Salida");
	
	private String value;
	
	private TipoOperacionKardex() {}
	
	private TipoOperacionKardex(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
