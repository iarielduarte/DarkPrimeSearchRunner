package com.chromia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIA")
@NamedQueries({
		@NamedQuery(name = Categoria.GET_CATEGORIA_BY_ID, query = Categoria.GET_CATEGORIA_BY_ID_QUERY),
		@NamedQuery(name = Categoria.GET_ALL_CATEGORIAS, query = Categoria.GET_ALL_CATEGORIAS_QUERY) })
public class Categoria {

	static final String GET_CATEGORIA_BY_ID_QUERY = "from Categoria c where c.id = :id";
	public static final String GET_CATEGORIA_BY_ID = "GET_CATEGORIA_BY_ID";

	static final String GET_ALL_CATEGORIAS_QUERY = "from Categoria";
	public static final String GET_ALL_CATEGORIAS = "GET_ALL_CATEGORIAS";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cat_codigo")
	private Integer id;

	@Column(name = "cat_nombre")
	private String nombre;

	@Column(name = "cat_porce_ganancia")
	private Double ganancia;

	@Column(name = "cat_porce_descuento")
	private Double descuento;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Cliente> clientes;

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public void setGanancia(Double ganancia) {
		this.ganancia = ganancia;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getDescuento() {
		return descuento;
	}

	public Double getGanancia() {
		return ganancia;
	}
}
