package com.chromia.controller;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;

import com.chromia.model.Articulo;
import com.chromia.model.TipoGrupo;
import com.chromia.service.IArticuloService;
import com.chromia.service.IGrupoService;
import com.chromia.service.IMarcaService;
import com.chromia.service.IPaisService;
import com.chromia.service.ITipoGrupoService;
import com.chromia.service.IUbicacionService;

@ManagedBean(name = "articuloMBean")
@SessionScoped
public class ArticuloManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{GrupoService}")
	private IGrupoService grupoService;

	@ManagedProperty(value = "#{TipoGrupoService}")
	private ITipoGrupoService tipoGrupoService;

	@ManagedProperty(value = "#{MarcaService}")
	private IMarcaService marcaService;

	@ManagedProperty(value = "#{UbicacionService}")
	private IUbicacionService ubicacionService;

	@ManagedProperty(value = "#{PaisService}")
	private IPaisService paisService;

	@ManagedProperty(value = "#{ArticuloService}")
	private IArticuloService articuloService;

	public String MiCodigo = "";
	private Integer id;
	private String nombre;
	private Integer marcaId;
	private Integer grupoId;
	private Integer tipoGrupoId;
	private Integer paisId;
	private Integer ubicacionId;
	private String codigoBarra;
	private String nombreFactura;
	private String codigoOrigen;
	private String numeroPieza;
	private String codigoMarca;
	private Double precioActual;
	private Double precioAnterior;
	private Double precioVenta;
	private String tipoIva;
	private Integer stockMinimo;
	private String observaciones;
	private Integer iva;

	private Integer filterGrupoId = 0;

	private List<Articulo> articulos;
	private List<Articulo> filteredArticulos;
	private Articulo selectedArticulo;
	private Articulo editArticulo;
	private Boolean confirmaGrupo = false;
	private List<SelectItem> selectOneItemTipoGrupo;

	@PostConstruct
	public void inicializar() {
		articulos = getArticuloService().getArticulos();
	}

	/* TODO: Getters...and...Setters */

	public String getNombre() {
		return nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(Integer marcaId) {
		this.marcaId = marcaId;
	}

	public Integer getGrupoId() {
		return grupoId;
	}

	public void setGrupoId(Integer grupoId) {
		this.grupoId = grupoId;
	}

	public Integer getTipoGrupoId() {
		return tipoGrupoId;
	}

	public void setTipoGrupoId(Integer tipoGrupoId) {
		this.tipoGrupoId = tipoGrupoId;
	}

	public Integer getPaisId() {
		return paisId;
	}

	public void setPaisId(Integer paisId) {
		this.paisId = paisId;
	}

	public Integer getUbicacionId() {
		return ubicacionId;
	}

	public void setUbicacionId(Integer ubicacionId) {
		this.ubicacionId = ubicacionId;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getNombreFactura() {
		return nombreFactura;
	}

	public void setNombreFactura(String nombreFactura) {
		this.nombreFactura = nombreFactura;
	}

	public String getCodigoOrigen() {
		return codigoOrigen;
	}

	public void setCodigoOrigen(String codigoOrigen) {
		this.codigoOrigen = codigoOrigen;
	}

	public String getNumeroPieza() {
		return numeroPieza;
	}

	public void setNumeroPieza(String numeroPieza) {
		this.numeroPieza = numeroPieza;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public Double getPrecioActual() {
		return precioActual;
	}

	public void setPrecioActual(Double precioActual) {
		this.precioActual = precioActual;
	}

	public Double getPrecioAnterior() {
		return precioAnterior;
	}

	public void setPrecioAnterior(Double precioAnterior) {
		this.precioAnterior = precioAnterior;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public String getTipoIva() {
		return tipoIva;
	}

	public void setTipoIva(String tipoIva) {
		this.tipoIva = tipoIva;
	}

	public Integer getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(Integer stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getIva() {
		return iva;
	}

	public void setIva(Integer iva) {
		this.iva = iva;
	}

	public Boolean getConfirmaGrupo() {
		return confirmaGrupo;
	}

	public void setConfirmaGrupo(Boolean confirmaGrupo) {
		this.confirmaGrupo = confirmaGrupo;
	}

	// Fin de atributos

	public IArticuloService getArticuloService() {
		return articuloService;
	}

	public void setArticuloService(IArticuloService articuloService) {
		this.articuloService = articuloService;
	}

	public IGrupoService getGrupoService() {
		return grupoService;
	}

	public void setGrupoService(IGrupoService grupoService) {
		this.grupoService = grupoService;
	}

	public ITipoGrupoService getTipoGrupoService() {
		return tipoGrupoService;
	}

	public void setTipoGrupoService(ITipoGrupoService tipoGrupoService) {
		this.tipoGrupoService = tipoGrupoService;
	}

	public IMarcaService getMarcaService() {
		return marcaService;
	}

	public void setMarcaService(IMarcaService marcaService) {
		this.marcaService = marcaService;
	}

	public IUbicacionService getUbicacionService() {
		return ubicacionService;
	}

	public void setUbicacionService(IUbicacionService ubicacionService) {
		this.ubicacionService = ubicacionService;
	}

	public IPaisService getPaisService() {
		return paisService;
	}

	public void setPaisService(IPaisService paisService) {
		this.paisService = paisService;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	public Articulo getSelectedArticulo() {
		if (selectedArticulo == null)
			selectedArticulo = getArticuloService().getArticulos().get(0);
		return selectedArticulo;
	}

	public void setSelectedArticulo(Articulo selectedArticulo) {
		this.selectedArticulo = selectedArticulo;
	}

	public Articulo getEditArticulo() {
		return editArticulo;
	}

	public void setEditArticulo(Articulo editArticulo) {
		this.editArticulo = editArticulo;
	}

	public List<Articulo> getFilteredArticulos() {
		return filteredArticulos;
	}

	public void setFilteredArticulos(List<Articulo> filteredArticulos) {
		this.filteredArticulos = filteredArticulos;
	}

	public Integer getFilterGrupoId() {
		return filterGrupoId;
	}

	public void setFilterGrupoId(Integer filterGrupoId) {
		this.filterGrupoId = filterGrupoId;
	}

	// TODO: Action Listener

	public void onCreate(ActionEvent actionEvent) {
		try {
			if (verificarNombreDuplicado(this.articuloAdd().getNombre())) {
				if (getArticuloService().addArticulo(this.articuloAdd())) {
					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_INFO, "Exito : ",
							"El articulo " + articuloAdd().getNombre()
									+ " se guardo con éxito :)");
					FacesContext.getCurrentInstance().addMessage(null, message);
					this.reset();
					this.onReset();
				} else {
					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Error : ",
							"El articulo " + articuloAdd().getNombre()
									+ " no se pudo guardo :(");
					FacesContext.getCurrentInstance().addMessage(null, message);
				}
			}

		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_FATAL, "Error de Conexión: ",
					"Error en el acceso a la base de datos, Detalle: "
							+ e.getMessage() + " x(");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public void onEdit(ActionEvent actionEvent) {
		try {
			if (getArticuloService().updateArticulo(getSelectedArticulo())) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Exito : ", "El articulo "
								+ this.selectedArticulo.getNombre()
								+ " se modifico con éxito :)");
				FacesContext.getCurrentInstance().addMessage(null, message);
				this.reset();
				this.onReset();
			} else {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error : ", "El articulo "
								+ this.selectedArticulo.getNombre()
								+ " no se pudo modificar :(");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",
					"Error en el acceso a la base de datos, Detalle: "
							+ e.getMessage() + " x(");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void onDelete(ActionEvent actionEvent) {
		try {
			if (getArticuloService().deleteArticulo(getSelectedArticulo())) {
				onReset();
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Exito : ", "El articulo "
								+ getSelectedArticulo().getNombre()
								+ " fue eliminado con éxito :)");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error : ", "El articulo "
								+ getSelectedArticulo().getNombre()
								+ " no se pudo eliminar :(");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",
					"Error en el acceso a la base de datos, Detalle: "
							+ e.getMessage() + " x(");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void onReset() {
		articulos = new ArrayList<Articulo>();
		articulos.addAll(getArticuloService().getArticulos());
		filteredArticulos = new ArrayList<Articulo>();
		filteredArticulos.addAll(articulos);
	}

	public Articulo articuloAdd() {
		Articulo add = new Articulo();
		add.setNombre(getNombre());
		add.setGrupo(getGrupoService().getGrupoById(getGrupoId()));
		add.setTipoGrupo(getTipoGrupoService().getTipoGrupoById(
				getTipoGrupoId()));
		add.setMarca(getMarcaService().getMarcaById(getMarcaId()));
		add.setPais(getPaisService().getPaisById(getPaisId()));
		add.setUbicacion(getUbicacionService().getUbicacionById(
				getUbicacionId()));
		add.setCodigoBarra(getCodigoBarra());
		add.setNombreFactura(getNombre());
		add.setCodigoOrigen(getCodigoOrigen());
		add.setNumeroPieza(getNumeroPieza());
		add.setCodigoMarca(getCodigoMarca());
		add.setPrecioActual(getPrecioActual());
		add.setPrecioAnterior(0.00);
		add.setPrecioVenta(getPrecioVenta());
		add.setTipoIva(getTipoIva());
		add.setStockMinimo(getStockMinimo());
		add.setObservaciones(getObservaciones());
		add.setIva(getIva());
		return add;
	}

	public void onConfirmaGrupo(ActionEvent actionEvent) {
		if (getGrupoId() > 0 && getMarcaId() > 0 && getPaisId() > 0
				&& getTipoGrupoId() > 0) {
			confirmaGrupo = true;
			this.setNombre(this.generarDescripcion());
			this.setCodigoOrigen(this.generarCodigoArticulo());
		} else {
			String referncia = "";

			if (getPaisId() == 0)
				referncia = "Procedencia";
			if (getTipoGrupoId() == 0)
				referncia = "Tipo de Grupo";
			if (getMarcaId() == 0)
				referncia = "Marca";
			if (getGrupoId() == 0)
				referncia = "Grupo";

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, referncia
							+ " es una selección obligatoria", ""));
		}
	}

	public void onModificaGrupo(ActionEvent actionEvent) {
		this.reset();
	}

	public void reset() {
		confirmaGrupo = false;
		this.setGrupoId(0);
		this.setMarcaId(0);
		this.setPaisId(0);
		this.setTipoGrupoId(0);
		this.setUbicacionId(0);
		this.setNombre(null);
		this.setCodigoOrigen(null);
		this.setCodigoBarra(null);
		this.setNombreFactura(null);
		this.setNumeroPieza(null);
		this.setCodigoMarca(null);
		this.setPrecioActual(null);
		this.setPrecioAnterior(null);
		this.setPrecioVenta(null);
		this.setIva(null);
		this.setTipoIva(null);
		this.setStockMinimo(null);
		this.setObservaciones(null);
		this.setFilterGrupoId(0);
		RequestContext.getCurrentInstance().reset("formArticulo");
	}

	public String generarDescripcion() {
		String tipo = getTipoGrupoService().getTipoGrupoById(getTipoGrupoId())
				.getNombre();
		String marca = getMarcaService().getMarcaById(getMarcaId()).getNombre();
		String pais = getPaisService().getPaisById(getPaisId()).getGentilicio();
		StringBuffer strBuff = new StringBuffer();
		strBuff.append(tipo + " ");
		strBuff.append(marca + " " + pais);
		return strBuff.toString();

	}

	public String generarCodigoArticulo() {
		Integer maxId = getArticuloService().getMaxId();
		System.out.println("Codigo Maximo :" + maxId);
		String tipo = getTipoGrupoService().getTipoGrupoById(getTipoGrupoId())
				.getNombre();
		tipo = new String(tipo.substring(0, 2));
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("A" + tipo + "0");
		strBuff.append(maxId + 1);
		return strBuff.toString();
	}

	public boolean verificarNombreDuplicado(String name) {
		Articulo a = new Articulo();
		try {
			a = getArticuloService().getArticuloByName(name);
			if (!a.equals(null)) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"Esta descripción ya se encuentra en registrada",
										""));
				return false;
			}
		} catch (java.lang.NullPointerException e) {
			a = null;
		}

		return true;
	}

	public void printLogAtributteValue() {
		System.out.println("Nombre :" + this.articuloAdd().getNombre());
		System.out.println("Grupo :"
				+ this.articuloAdd().getGrupo().getNombre());
		System.out.println("TipoGrupo :"
				+ this.articuloAdd().getTipoGrupo().getNombre());
		System.out.println("Marca :"
				+ this.articuloAdd().getMarca().getNombre());
		System.out.println("Pais :" + this.articuloAdd().getPais().getNombre());
		System.out.println("Ubicacion :"
				+ this.articuloAdd().getUbicacion().getNombre());
		System.out.println("CodigoBarra :"
				+ this.articuloAdd().getCodigoBarra());
		System.out.println("NombreFactura :"
				+ this.articuloAdd().getNombreFactura());
		System.out.println("CodigoOrigen :"
				+ this.articuloAdd().getCodigoOrigen());
		System.out.println("NumeroPieza :"
				+ this.articuloAdd().getNumeroPieza());
		System.out.println("CodigoMarca :"
				+ this.articuloAdd().getCodigoMarca());
		System.out.println("PrecioActual :"
				+ this.articuloAdd().getPrecioActual());
		System.out.println("PrecioAnterior :"
				+ this.articuloAdd().getPrecioVenta());
		System.out.println("PrecioVenta :"
				+ this.articuloAdd().getPrecioVenta());
		System.out.println("TipoIva :" + this.articuloAdd().getTipoIva());
		System.out.println("StockMinimo :"
				+ this.articuloAdd().getStockMinimo());
		System.out.println("Observaciones :"
				+ this.articuloAdd().getObservaciones());
		System.out.println("Iva% :" + this.articuloAdd().getIva());
	}

	/*
	 * Este metodo es el que trabaja con el Ajax del DropDown de Grupo para
	 * filtrar los Tipos de Grupos por el Grupo Id <p:ajax update="somTipoGrupo"
	 * listener="#{articuloMBean.handleGrupoChange}" />
	 */
	public void handleGrupoChange() {
		if (grupoId != null && grupoId != 0)
			this.setFilterGrupoId(grupoId);
		else
			this.setFilterGrupoId(0);

	}

	public List<SelectItem> getSelectOneItemTipoGrupo() {
		selectOneItemTipoGrupo = new ArrayList<SelectItem>();
		List<TipoGrupo> tipoGrupos;
		int id = getFilterGrupoId();
		if (id == 0) {
			tipoGrupos = getTipoGrupoService().getTipoGrupos();
		} else {
			tipoGrupos = getTipoGrupoService().getTipoGruposByGrupo(id);
		}
		for (TipoGrupo tipoGrupo : tipoGrupos) {
			SelectItem selectItem = new SelectItem(tipoGrupo.getId(),
					tipoGrupo.getNombre());
			selectOneItemTipoGrupo.add(selectItem);
		}
		return selectOneItemTipoGrupo;
	}

	/*
	 * Page Navigation
	 */
	public String moveToPageAddArticulo() {
		return "addArticulo";
	}

	public String moveToPageArticulo() {
		return "articulo";
	}

	public String moveToPageEditArticulo() {
		return "editArticulo";
	}

	public void onRowSelect(SelectEvent event) {
		System.out
				.println("***********************************************************");
		System.out.println("Seleccion :"
				+ getSelectedArticulo().getCodigoOrigen());
		this.setEditArticulo(getSelectedArticulo());
		System.out
				.println("***********************************************************");
		this.MiCodigo = String.valueOf(getSelectedArticulo().getId());
		ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext
				.getCurrentInstance().getApplication().getNavigationHandler();

		configurableNavigationHandler
				.performNavigation("editarticulo.xhtml?faces-redirect=true");
	}

	/**
	 * @author Ariel Duarte
	 * @since 10/05/2014 Integracion de JasperReport con JSF
	 */
	JasperPrint jasperPrint;
	HttpServletResponse httpServletResponse;
	ServletOutputStream servletOutputStream;

	public void init() throws JRException {
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
				articulos);
		String reportPath = FacesContext.getCurrentInstance()
				.getExternalContext().getRealPath("/reports/articulos.jasper");
		jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(),
				beanCollectionDataSource);
		httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
	}

	/**
	 * Reporte en PDF
	 * 
	 * @throws JRException
	 * @throws IOException
	 */
	public void PDF() throws JRException, IOException {
		init();
		// httpServletResponse.addHeader("Content-disposition",
		// "attachment; filename=articulos.pdf");
		servletOutputStream = httpServletResponse.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,
				servletOutputStream);
		FacesContext.getCurrentInstance().responseComplete();

	}

	/**
	 * Reporte en Excel
	 * 
	 * @throws JRException
	 * @throws IOException
	 */
	public void XLSX() throws JRException, IOException {
		init();
		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=articulos.xlsx");
		servletOutputStream = httpServletResponse.getOutputStream();
		JRXlsxExporter docxExporter = new JRXlsxExporter();
		docxExporter
				.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		docxExporter.exportReport();
		FacesContext.getCurrentInstance().responseComplete();
	}
}
