package com.chromia.model;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

import java.util.ArrayList;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="GRUPO")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries(
		{
			@NamedQuery(name=Grupo.GET_GRUPO_BY_ID, query=Grupo.GET_GRUPO_BY_ID_QUERY),
			@NamedQuery(name=Grupo.GET_ALL_GRUPOS, query=Grupo.GET_ALL_GRUPOS_QUERY)
		}
)

public class Grupo {

	static final String GET_GRUPO_BY_ID_QUERY = "from Grupo g where g.id = :id"; 
	public static final String GET_GRUPO_BY_ID = "GET_GRUPO_BY_ID"; 
	
	static final String GET_ALL_GRUPOS_QUERY = "from Grupo"; 
	public static final String GET_ALL_GRUPOS = "GET_ALL_GRUPOS";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "gru_codigo")
	private Integer id;
	
	@Column(name="gru_nombre")
	private String nombre;
	
	@OneToMany(mappedBy="grupo", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Articulo> articulos;
	 
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

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}
	
}
