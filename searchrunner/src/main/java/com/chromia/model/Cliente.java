package com.chromia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTE")
@NamedQueries({
		@NamedQuery(name = Cliente.GET_CLIENTE_BY_ID, query = Cliente.GET_CLIENTE_BY_ID_QUERY),
		@NamedQuery(name = Cliente.GET_ALL_CLIENTES, query = Cliente.GET_ALL_CLIENTES_QUERY),
		@NamedQuery(name = Cliente.GET_CLIENTE_BY_CIUDAD_ID, query = Cliente.GET_CLIENTE_BY_CIUDAD_ID_QUERY),
		@NamedQuery(name = Cliente.GET_CLIENTE_BY_CATEGORIA_ID, query = Cliente.GET_CLIENTE_BY_CATEGORIA_ID_QUERY) })
public class Cliente {

	static final String GET_CLIENTE_BY_ID_QUERY = "FROM Cliente C LEFT JOIN FETCH C.ciudad G LEFT JOIN FETCH C.categoria T WHERE C.id = :id";
	public static final String GET_CLIENTE_BY_ID = "GET_CLIENTE_BY_ID";

	static final String GET_ALL_CLIENTES_QUERY = "FROM Cliente C LEFT JOIN FETCH C.ciudad G LEFT JOIN FETCH C.categoria T";
	public static final String GET_ALL_CLIENTES = "GET_ALL_CLIENTES";

	static final String GET_CLIENTE_BY_CIUDAD_ID_QUERY = "SELECT c FROM Cliente AS c LEFT JOIN FETCH c.ciudad AS i where i.id = :id";
	public static final String GET_CLIENTE_BY_CIUDAD_ID = "GET_CLIENTE_BY_CIUDAD_ID";

	static final String GET_CLIENTE_BY_CATEGORIA_ID_QUERY = "SELECT c FROM Cliente AS c LEFT JOIN FETCH c.categoria AS a where a.id = :id";
	public static final String GET_CLIENTE_BY_CATEGORIA_ID = "GET_CLIENTE_BY_CATEGORIA_ID";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clie_codigo")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ciu_codigo", referencedColumnName = "ciu_codigo")
	private Ciudad ciudad;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "cat_codigo", referencedColumnName = "cat_codigo")
	private Categoria categoria;

	@Column(name = "clie_razon", unique = true)
	private String nombre;

	@Column(name = "clie_direcc", unique = true)
	private String direccion;

	@Column(name = "clie_telef", unique = true)
	private String telefono;

	@Column(name = "clie_movil", unique = true)
	private String celular;

	@Column(name = "clie_ruc", unique = true)
	private String ruc;

	@Column(name = "clie_correo", unique = true)
	private String email;

	@Column(name = "clie_comen")
	private String comentario;

	@Column(name = "clie_cip", unique = true)
	private String cedula;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
}
