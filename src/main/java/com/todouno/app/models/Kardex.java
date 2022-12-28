package com.todouno.app.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;

import com.todouno.app.enums.TipoOperacionKardex;

@Entity
public class Kardex implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_sequence_kardex")
	@SequenceGenerator(name="id_sequence_kardex", sequenceName = "sq_id_kardex", allocationSize = 1, initialValue = 1)
	private Long id;
	
	private TipoOperacionKardex tipoOperacion;
	
	@OneToOne
	private Producto producto;
	
	private String descripcion;
	
	private Integer cantidad;
	
	private Double valor;
	
	private Date fecha;
	
	@PrePersist
	public void prePersist() {
		this.fecha = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoOperacionKardex getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(TipoOperacionKardex tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	private static final long serialVersionUID = -8336764207287923605L;
}
