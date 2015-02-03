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
@Table(name = "PROVEEDOR")
@NamedQueries({
		@NamedQuery(name = Proveedor.GET_PROVEEDOR_BY_ID, query = Proveedor.GET_PROVEEDOR_BY_ID_QUERY),
		@NamedQuery(name = Proveedor.GET_ALL_PROVEEDORES, query = Proveedor.GET_ALL_PROVEEDORES_QUERY) })
public class Proveedor {

	static final String GET_PROVEEDOR_BY_ID_QUERY = "FROM Proveedor P LEFT JOIN FETCH P.ciudad C LEFT JOIN FETCH P.pais T WHERE P.id = :id";
	public static final String GET_PROVEEDOR_BY_ID = "GET_PROVEEDOR_BY_ID";

	static final String GET_ALL_PROVEEDORES_QUERY = "FROM Proveedor P LEFT JOIN FETCH P.ciudad C LEFT JOIN FETCH P.pais T";
	public static final String GET_ALL_PROVEEDORES = "GET_ALL_PROVEEDORES";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pro_codigo")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ciu_codigo", referencedColumnName = "ciu_codigo")
	private Ciudad ciudad;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "pais_codigo", referencedColumnName = "pais_codigo")
	private Pais pais;

	@Column(name = "pro_razon", unique = true)
	private String nombre;

	@Column(name = "pro_ruc", unique = true)
	private String ruc;

	@Column(name = "pro_direc", unique = true)
	private String direccion;

	@Column(name = "pro_telef", unique = true)
	private String telefono;

	@Column(name = "pro_fax", unique = true)
	private String celular;

	@Column(name = "pro_mail", unique = true)
	private String email;

	@Column(name = "pro_iva", unique = true)
	private Integer pro_iva;

}
