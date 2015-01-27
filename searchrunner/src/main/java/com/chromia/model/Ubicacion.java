package com.chromia.model;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.NamedQuery;



@Entity
@Table(name="UBICACION")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries(
		{
			@NamedQuery(name=Ubicacion.GET_UBICACION_BY_ID, query=Ubicacion.GET_UBICACION_BY_ID_QUERY),
			@NamedQuery(name=Ubicacion.GET_ALL_UBICACIONES, query=Ubicacion.GET_ALL_UBICACIONES_QUERY)
		}
)
public class Ubicacion {

	static final String GET_UBICACION_BY_ID_QUERY = "from Ubicacion U where U.id = :id"; 
	public static final String GET_UBICACION_BY_ID = "GET_UBICACION_BY_ID"; 
	
	static final String GET_ALL_UBICACIONES_QUERY = "from Ubicacion"; 
	public static final String GET_ALL_UBICACIONES = "GET_ALL_UBICACIONES";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ubica_codigo")
	private Integer id;
	
	@Column(name="ubica_nombre")
	private String nombre;
	
	@OneToMany(mappedBy="ubicacion", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Articulo> articulos;

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

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
	

	
	
	
	
	
}
