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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ARTICULO")
@NamedQueries({
		@NamedQuery(name = Articulo.GET_ARTICULO_BY_ID, query = Articulo.GET_ARTICULO_BY_ID_QUERY),
		@NamedQuery(name = Articulo.GET_ALL_ARTICULOS, query = Articulo.GET_ALL_ARTICULOS_QUERY) })
public class Articulo {

	static final String GET_ARTICULO_BY_ID_QUERY = "FROM Articulo A LEFT JOIN FETCH A.grupo G LEFT JOIN FETCH A.tipoGrupo T LEFT JOIN FETCH A.marca M LEFT JOIN FETCH A.pais P LEFT JOIN FETCH A.ubicacion U WHERE A.id = :id";
	public static final String GET_ARTICULO_BY_ID = "GET_ARTICULO_BY_ID";

	static final String GET_ALL_ARTICULOS_QUERY = "FROM Articulo A LEFT JOIN FETCH A.grupo G LEFT JOIN FETCH A.tipoGrupo T LEFT JOIN FETCH A.marca M LEFT JOIN FETCH A.pais P LEFT JOIN FETCH A.ubicacion U";
	public static final String GET_ALL_ARTICULOS = "GET_ALL_ARTICULOS";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "art_codigo")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "gru_codigo", referencedColumnName = "gru_codigo")
	private Grupo grupo;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "tg_codigo", referencedColumnName = "tg_codigo")
	private TipoGrupo tipoGrupo;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "mar_codigo", referencedColumnName = "mar_codigo")
	private Marca marca;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "pais_codigo", referencedColumnName = "pais_codigo")
	private Pais pais;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ubica_codigo", referencedColumnName = "ubica_codigo")
	private Ubicacion ubicacion;

	@Column(name = "artnomreal", unique = true)
	private String nombre;

	@Column(name = "artbarra")
	private String codigoBarra;

	@Column(name = "artnomfact")
	private String nombreFactura;

	@Column(name = "artcodorigen")
	private String codigoOrigen;

	@Column(name = "artnropieza")
	private String numeroPieza;

	@Column(name = "codigomarca")
	private String codigoMarca;

	@Column(name = "artprecact")
	private Double precioActual;

	@Column(name = "artpreant")
	private Double precioAnterior;

	@Column(name = "artpreventa")
	private Double precioVenta;

	@Column(name = "arttpiva")
	private String tipoIva;

	@Column(name = "artstockmin")
	private Integer stockMinimo;

	@Column(name = "artobs")
	private String observaciones;

	@Column(name = "artivaporc")
	private Integer iva;

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public String getCodigoOrigen() {
		return codigoOrigen;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public Integer getId() {
		return id;
	}

	public Integer getIva() {
		return iva;
	}

	public Marca getMarca() {
		return marca;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNombreFactura() {
		return nombreFactura;
	}

	public String getNumeroPieza() {
		return numeroPieza;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public Pais getPais() {
		return pais;
	}

	public Double getPrecioActual() {
		return precioActual;
	}

	public Double getPrecioAnterior() {
		return precioAnterior;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public Integer getStockMinimo() {
		return stockMinimo;
	}

	public TipoGrupo getTipoGrupo() {
		return tipoGrupo;
	}

	public String getTipoIva() {
		return tipoIva;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public void setCodigoOrigen(String codigoOrigen) {
		this.codigoOrigen = codigoOrigen;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIva(Integer iva) {
		this.iva = iva;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNombreFactura(String nombreFactura) {
		this.nombreFactura = nombreFactura;
	}

	public void setNumeroPieza(String numeroPieza) {
		this.numeroPieza = numeroPieza;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public void setPrecioActual(Double precioActual) {
		this.precioActual = precioActual;
	}

	public void setPrecioAnterior(Double precioAnterior) {
		this.precioAnterior = precioAnterior;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public void setStockMinimo(Integer stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public void setTipoGrupo(TipoGrupo tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	public void setTipoIva(String tipoIva) {
		this.tipoIva = tipoIva;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

}
