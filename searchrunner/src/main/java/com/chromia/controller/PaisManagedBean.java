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

import com.chromia.model.Pais;
import com.chromia.service.IPaisService;

@ManagedBean(name = "paisMBean")
@ViewScoped
@SessionScoped
public class PaisManagedBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{PaisService}")
	private IPaisService paisService;
	private String nombre;
	private String gentilicio;
	private List<Pais> paises;
	private List<Pais> filteredPaises;  
	private Pais selectedPais; 
	
	@PostConstruct
	public void inicializar() {
    	paises = getPaisService().getPaises();
		
	}
	
	
	private List<SelectItem> selectOneItemPais;
	
	
	/*TODO: Getters...and...Setters*/
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}

	public String getGentilicio() {
		return gentilicio;
	}

	public void setGentilicio(String gentilicio) {
		this.gentilicio = gentilicio;
	}

	public IPaisService getPaisService() {
		return paisService;
	}

	public void setPaisService(IPaisService paisService) {
		this.paisService = paisService;
	}

	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	public List<SelectItem> getSelectOneItemPais() {
		selectOneItemPais = new ArrayList<SelectItem>();
		List<Pais> paises = getPaisService().getPaises();
		for (Pais pais : paises) {
			SelectItem selectItem = new SelectItem(pais.getId(), pais.getGentilicio());
			selectOneItemPais.add(selectItem);
		}
		return selectOneItemPais;
	}

	public Pais getSelectedPais() {
		if(selectedPais==null)
			selectedPais = getPaisService().getPaises().get(0); 
		return selectedPais;
	}

	public void setSelectedPais(Pais selectedPais) {
		this.selectedPais = selectedPais;
	}

	public List<Pais> getFilteredPaises() {
		return filteredPaises;
	}

	public void setFilteredPaises(List<Pais> filteredPaises) {
		this.filteredPaises = filteredPaises;
	}

//	TODO: Action Listener
	
	public void onCreate(ActionEvent actionEvent) {
		try {
			Pais paisAdd = new Pais();
			paisAdd.setNombre(getNombre());
			paisAdd.setGentilicio(getGentilicio());
			
		    if(getPaisService().addPais(paisAdd))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "El pais "+paisAdd.getNombre()+" se guardo con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "El pais "+paisAdd.getNombre()+" no se pudo guardo :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión: ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	
	public void onEdit(ActionEvent actionEvent) {
		try {
			
		    if(getPaisService().updatePais(getSelectedPais()))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "El pais "+this.selectedPais.getNombre()+" se modifico con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "El pais "+this.selectedPais.getNombre()+" no se pudo modificar :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void onDelete(ActionEvent actionEvent) {
	    try {
			
		    if(getPaisService().deletePais(getSelectedPais()))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "El pais "+getSelectedPais().getNombre()+" fue eliminado con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "El pais "+getSelectedPais().getNombre()+" no se pudo eliminar :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void onReset(){
		paises = new ArrayList<Pais>();
		paises.addAll(getPaisService().getPaises());
		filteredPaises  = new ArrayList<Pais>();
		filteredPaises.addAll(paises);
	}

}
