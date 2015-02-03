package com.chromia.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.springframework.dao.DataAccessException;

import com.chromia.model.Categoria;
import com.chromia.service.ICategoriaService;

@ManagedBean(name = "categoriaMBean")
@ViewScoped
@SessionScoped
public class CategoriaManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{CategoriaService}")
	private ICategoriaService categoriaService;
	private String nombre;
	private Double ganancia;
	private Double descuento;
	private List<Categoria> categorias;
	private List<Categoria> filteredCategorias;
	private Categoria selectedCategoria;
	private List<SelectItem> selectOneItemCategoria;

	@PostConstruct
	public void inicializar() {
		categorias = getCategoriaService().getCategorias();
	}

	public ICategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(ICategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getGanancia() {
		return ganancia;
	}

	public void setGanancia(Double ganancia) {
		this.ganancia = ganancia;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Categoria> getFilteredCategorias() {
		return filteredCategorias;
	}

	public void setFilteredCategorias(List<Categoria> filteredCategorias) {
		this.filteredCategorias = filteredCategorias;
	}

	public Categoria getSelectedCategoria() {
		if (selectedCategoria == null)
			selectedCategoria = getCategoriaService().getCategorias().get(0);
		return selectedCategoria;
	}

	public void setSelectedCategoria(Categoria selectedCategoria) {
		this.selectedCategoria = selectedCategoria;
	}

	public List<SelectItem> getSelectOneItemCategoria() {
		selectOneItemCategoria = new ArrayList<SelectItem>();
		List<Categoria> categorias = getCategoriaService().getCategorias();
		for (Categoria c : categorias) {
			SelectItem selectItem = new SelectItem(c.getId(), c.getNombre());
			selectOneItemCategoria.add(selectItem);
		}
		return selectOneItemCategoria;
	}

	public void setSelectOneItemCategoria(
			List<SelectItem> selectOneItemCategoria) {
		this.selectOneItemCategoria = selectOneItemCategoria;
	}

	// TODO: Action Listener

	public void onCreate(ActionEvent actionEvent) {
		try {
			Categoria categoriaAdd = new Categoria();
			categoriaAdd.setNombre(getNombre());
			categoriaAdd.setDescuento(getDescuento());
			categoriaAdd.setGanancia(getGanancia());

			if (getCategoriaService().addCategoria(categoriaAdd)) {
				onReset();
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Exito : ", "La Categoria "
								+ categoriaAdd.getNombre()
								+ " se guardo con éxito :)");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error : ",
						"La Categoria " + categoriaAdd.getNombre()
								+ " no se pudo guardo :(");
				FacesContext.getCurrentInstance().addMessage(null, message);
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

			if (getCategoriaService().updateCategoria(getSelectedCategoria())) {
				onReset();
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Exito : ", "La Categoria "
								+ this.selectedCategoria.getNombre()
								+ " se modifico con éxito :)");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error : ",
						"La Categoria " + this.selectedCategoria.getNombre()
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
			if (getCategoriaService().deleteCategoria(getSelectedCategoria())) {
				onReset();
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Exito : ", "La Categoria "
								+ getSelectedCategoria().getNombre()
								+ " fue eliminado con éxito :)");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error : ",
						"La Categoria " + getSelectedCategoria().getNombre()
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
		categorias = new ArrayList<Categoria>();
		categorias.addAll(getCategoriaService().getCategorias());
		filteredCategorias = new ArrayList<Categoria>();
		filteredCategorias.addAll(categorias);
	}

}
