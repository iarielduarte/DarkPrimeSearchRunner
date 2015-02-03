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

import com.chromia.model.Ciudad;
import com.chromia.service.ICiudadService;

@ManagedBean(name = "ciudadMBean")
@ViewScoped
@SessionScoped
public class CiudadManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{CiudadService}")
	private ICiudadService ciudadService;
	private String nombre;
	private List<Ciudad> ciudades;
	private List<Ciudad> filteredCiudades;
	private Ciudad selectedCiudad;
	private List<SelectItem> selectOneItemCiudad;

	@PostConstruct
	public void inicializar() {
		ciudades = getCiudadService().getCiudades();
	}

	public ICiudadService getCiudadService() {
		return ciudadService;
	}

	public void setCiudadService(ICiudadService ciudadService) {
		this.ciudadService = ciudadService;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public List<Ciudad> getFilteredCiudades() {
		return filteredCiudades;
	}

	public void setFilteredCiudades(List<Ciudad> filteredCiudades) {
		this.filteredCiudades = filteredCiudades;
	}

	public Ciudad getSelectedCiudad() {
		if (selectedCiudad == null)
			selectedCiudad = getCiudadService().getCiudades().get(0);
		return selectedCiudad;
	}

	public void setSelectedCiudad(Ciudad selectedCiudad) {
		this.selectedCiudad = selectedCiudad;
	}

	public void setSelectOneItemCiudad(List<SelectItem> selectOneItemCiudad) {
		this.selectOneItemCiudad = selectOneItemCiudad;
	}

	public List<SelectItem> getSelectOneItemCiudad() {
		selectOneItemCiudad = new ArrayList<SelectItem>();
		List<Ciudad> ciudades = getCiudadService().getCiudades();
		for (Ciudad c : ciudades) {
			SelectItem selectItem = new SelectItem(c.getId(), c.getNombre());
			selectOneItemCiudad.add(selectItem);
		}
		return selectOneItemCiudad;
	}

	// TODO: Action Listener

	public void onCreate(ActionEvent actionEvent) {
		try {
			Ciudad ciudadAdd = new Ciudad();
			ciudadAdd.setNombre(getNombre());

			if (getCiudadService().addCiudad(ciudadAdd)) {
				onReset();
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Exito : ", "La ciudad "
								+ ciudadAdd.getNombre()
								+ " se guardo con éxito :)");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error : ", "La ciudad "
								+ ciudadAdd.getNombre()
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

			if (getCiudadService().updateCiudad(getSelectedCiudad())) {
				onReset();
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Exito : ", "La Ciudad "
								+ this.selectedCiudad.getNombre()
								+ " se modifico con éxito :)");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error : ", "La Ciudad "
								+ this.selectedCiudad.getNombre()
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
			if (getCiudadService().deleteCiudad(getSelectedCiudad())) {
				onReset();
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Exito : ", "La Ciudad "
								+ getSelectedCiudad().getNombre()
								+ " fue eliminado con éxito :)");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error : ", "La Ciudad "
								+ getSelectedCiudad().getNombre()
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
		ciudades = new ArrayList<Ciudad>();
		ciudades.addAll(getCiudadService().getCiudades());
		filteredCiudades = new ArrayList<Ciudad>();
		filteredCiudades.addAll(ciudades);
	}
}
