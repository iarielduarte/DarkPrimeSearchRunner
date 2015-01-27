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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.NamedQuery;



@Entity
@Table(name="MARCA")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries(
		{
			@NamedQuery(name=Marca.GET_MARCA_BY_ID, query=Marca.GET_MARCA_BY_ID_QUERY),
			@NamedQuery(name=Marca.GET_ALL_MARCAS, query=Marca.GET_ALL_MARCAS_QUERY)
		}
)
public class Marca {

	static final String GET_MARCA_BY_ID_QUERY = "from Marca m where m.id = :id"; 
	public static final String GET_MARCA_BY_ID = "GET_MARCA_BY_ID"; 
	
	static final String GET_ALL_MARCAS_QUERY = "from Marca"; 
	public static final String GET_ALL_MARCAS = "GET_ALL_MARCAS";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "mar_codigo")
	private Integer id;
	
	@Column(name="mar_nombre")
	private String nombre;

	@OneToMany(mappedBy="marca", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Articulo> articulos;
	
	

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Articulo> getArticulos() {
		return articulos;
	}
	
	
	
	
	
}
