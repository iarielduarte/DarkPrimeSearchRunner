package com.chromia.controller;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

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

import com.chromia.model.Ubicacion;
import com.chromia.service.IUbicacionService;

@ManagedBean(name = "ubicacionMBean")
@ViewScoped
@SessionScoped
public class UbicacionManagedBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{UbicacionService}")
	private IUbicacionService ubicacionService;
	private String nombre;
	private List<Ubicacion> ubicaciones;
	private List<Ubicacion> filteredUbicaciones;  
	private Ubicacion selectedUbicacion; 
	
	@PostConstruct
	public void inicializar() {
    	ubicaciones = getUbicacionService().getUbicaciones();
		
	}
	
	private List<SelectItem> selectOneItemUbicacion;
	
	
	/*TODO: Getters...and...Setters*/
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}

	public IUbicacionService getUbicacionService() {
		return ubicacionService;
	}

	public void setUbicacionService(IUbicacionService ubicacionService) {
		this.ubicacionService = ubicacionService;
	}

	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public List<SelectItem> getSelectOneItemUbicacion() {
		selectOneItemUbicacion = new ArrayList<SelectItem>();
		List<Ubicacion> ubicaciones = getUbicacionService().getUbicaciones();
		for (Ubicacion ubicacion : ubicaciones) {
			SelectItem selectItem = new SelectItem(ubicacion.getId(), ubicacion.getNombre());
			selectOneItemUbicacion.add(selectItem);
		}
		return selectOneItemUbicacion;
	}

	public Ubicacion getSelectedUbicacion() {
		if(selectedUbicacion==null)
			selectedUbicacion = getUbicacionService().getUbicaciones().get(0); 
		return selectedUbicacion;
	}

	public void setSelectedUbicacion(Ubicacion selectedUbicacion) {
		this.selectedUbicacion = selectedUbicacion;
	}

	public List<Ubicacion> getFilteredUbicaciones() {
		return filteredUbicaciones;
	}

	public void setFilteredUbicaciones(List<Ubicacion> filteredUbicaciones) {
		this.filteredUbicaciones = filteredUbicaciones;
	}

//	TODO: Action Listener
	
	public void onCreate(ActionEvent actionEvent) {
		try {
			Ubicacion ubicacionAdd = new Ubicacion();
			ubicacionAdd.setNombre(getNombre());
			
		    if(getUbicacionService().addUbicacion(ubicacionAdd))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "La ubicacion "+ubicacionAdd.getNombre()+" se guardo con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "La ubicacion "+ubicacionAdd.getNombre()+" no se pudo guardo :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión: ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	
	public void onEdit(ActionEvent actionEvent) {
		try {
			
		    if(getUbicacionService().updateUbicacion(getSelectedUbicacion()))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "La ubicacion "+this.selectedUbicacion.getNombre()+" se modifico con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "La ubicacion "+this.selectedUbicacion.getNombre()+" no se pudo modificar :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void onDelete(ActionEvent actionEvent) {
	    try {
			
		    if(getUbicacionService().deleteUbicacion(getSelectedUbicacion()))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "La ubicacion "+getSelectedUbicacion().getNombre()+" fue eliminado con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "La ubicacion "+getSelectedUbicacion().getNombre()+" no se pudo eliminar :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void onReset(){
		ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.addAll(getUbicacionService().getUbicaciones());
		filteredUbicaciones  = new ArrayList<Ubicacion>();
		filteredUbicaciones.addAll(ubicaciones);
	}

}
