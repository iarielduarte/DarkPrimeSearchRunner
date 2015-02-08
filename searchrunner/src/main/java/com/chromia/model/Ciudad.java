package com.chromia.model;

/**
 * @author Ariel Duarte
 * @since 28-Enero-2015
 * @version 1.0.0
 */

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
@Table(name = "CIUDAD")
@NamedQueries({
		@NamedQuery(name = Ciudad.GET_CIUDAD_BY_ID, query = Ciudad.GET_CIUDAD_BY_ID_QUERY),
		@NamedQuery(name = Ciudad.GET_ALL_CIUDADES, query = Ciudad.GET_ALL_CIUDADES_QUERY) })
public class Ciudad {

	static final String GET_CIUDAD_BY_ID_QUERY = "from Ciudad C where C.id = :id";
	public static final String GET_CIUDAD_BY_ID = "GET_CIUDAD_BY_ID";

	static final String GET_ALL_CIUDADES_QUERY = "from Ciudad";
	public static final String GET_ALL_CIUDADES = "GET_ALL_CIUDADES";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ciu_codigo", unique = true)
	private Integer id;

	@Column(name = "ciu_nombre", unique = true)
	private String nombre;

	@OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Cliente> clientes;
	
	@OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Proveedor> proveedores;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}
	
	
}
