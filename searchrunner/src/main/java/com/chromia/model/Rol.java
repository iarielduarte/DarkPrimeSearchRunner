package com.chromia.model;

/**
 * @author Ariel Duarte
 * @since 23-Oct-2013
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
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="ROL")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries(
		{
			@NamedQuery(name=Rol.GET_ROL_BY_ID, query=Rol.GET_ROL_BY_ID_QUERY),
			@NamedQuery(name=Rol.GET_ALL_ROLS, query=Rol.GET_ALL_ROLS_QUERY)
		}
)
public class Rol {

	static final String GET_ROL_BY_ID_QUERY = "from Rol r where r.id = :id"; 
	public static final String GET_ROL_BY_ID = "GET_ROL_BY_ID"; 
	
	static final String GET_ALL_ROLS_QUERY = "from Rol"; 
	public static final String GET_ALL_ROLS = "GET_ALL_ROLS";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ROL_ID")
	private Integer id;
	
	@Column(name="ROL_NOMBRE")
	private String nombre;

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
	
	@OneToMany(mappedBy="rol", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Usuario> usuarios = new ArrayList<Usuario>();

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	
	
}
