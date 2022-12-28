package com.todouno.app.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;

@Entity
public class Inventario implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_sequence_inventario")
	@SequenceGenerator(name="id_sequence_inventario", sequenceName = "sq_id_inventario", allocationSize = 1, initialValue = 1)
	private Long id;
	
	@OneToOne
	private Producto producto;
	
	private Integer cantidad;
	
	@PrePersist
	public void prePersist() {
		this.cantidad = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	private static final long serialVersionUID = -8223027339634711971L;
}
