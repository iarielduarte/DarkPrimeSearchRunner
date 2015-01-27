package com.chromia.model;

/**
 * @author Ariel Duarte
 * @since 23-Oct-2013
 * @version 1.0.0
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="USUARIO")
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "USU_ID")
	private Integer id;
	
	@Column(name="USU_NOMBRE")
	private String nombre;
	
	@Column(name="USU_CLAVE")
	private String clave;
	
	@Column(name="USU_EMAIL")
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ROL_ID")
    private Rol rol;

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

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
}
