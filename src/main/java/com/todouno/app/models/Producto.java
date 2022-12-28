package com.todouno.app.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Producto implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_sequence_producto")
	@SequenceGenerator(name="id_sequence_producto", sequenceName = "sq_id_producto", allocationSize = 1, initialValue = 1)
	private Long id;
	
	private String concepto;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	
	private static final long serialVersionUID = 1536635007240220650L;
}
