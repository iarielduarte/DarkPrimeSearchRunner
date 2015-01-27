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

import com.chromia.model.Marca;
import com.chromia.service.IMarcaService;

@ManagedBean(name = "marcaMBean")
@ViewScoped
@SessionScoped
public class MarcaManagedBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{MarcaService}")
	private IMarcaService marcaService;
	private String nombre;
	private List<Marca> marcas;
	private List<Marca> filteredMarcas;  
	private Marca selectedMarca; 
	
	@PostConstruct
	public void inicializar() {
    	marcas = getMarcaService().getMarcas();
		
	}
	
	private List<SelectItem> selectOneItemMarca;
	
	
	/*TODO: Getters...and...Setters*/
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}

	public IMarcaService getMarcaService() {
		return marcaService;
	}

	public void setMarcaService(IMarcaService marcaService) {
		this.marcaService = marcaService;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public List<SelectItem> getSelectOneItemMarca() {
		selectOneItemMarca = new ArrayList<SelectItem>();
		List<Marca> marcas = getMarcaService().getMarcas();
		for (Marca marca : marcas) {
			SelectItem selectItem = new SelectItem(marca.getId(), marca.getNombre());
			selectOneItemMarca.add(selectItem);
		}
		return selectOneItemMarca;
	}

	public Marca getSelectedMarca() {
		if(selectedMarca==null)
			selectedMarca = getMarcaService().getMarcas().get(0); 
		return selectedMarca;
	}

	public void setSelectedMarca(Marca selectedMarca) {
		this.selectedMarca = selectedMarca;
	}

	public List<Marca> getFilteredMarcas() {
		return filteredMarcas;
	}

	public void setFilteredMarcas(List<Marca> filteredMarcas) {
		this.filteredMarcas = filteredMarcas;
	}

//	TODO: Action Listener
	
	public void onCreate(ActionEvent actionEvent) {
		try {
			Marca marcaAdd = new Marca();
			marcaAdd.setNombre(getNombre());
			
		    if(getMarcaService().addMarca(marcaAdd))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "El marca "+marcaAdd.getNombre()+" se guardo con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "El marca "+marcaAdd.getNombre()+" no se pudo guardo :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión: ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	
	public void onEdit(ActionEvent actionEvent) {
		try {
			
		    if(getMarcaService().updateMarca(getSelectedMarca()))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "El marca "+this.selectedMarca.getNombre()+" se modifico con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "El marca "+this.selectedMarca.getNombre()+" no se pudo modificar :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void onDelete(ActionEvent actionEvent) {
	    try {
			
		    if(getMarcaService().deleteMarca(getSelectedMarca()))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "El marca "+getSelectedMarca().getNombre()+" fue eliminado con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "El marca "+getSelectedMarca().getNombre()+" no se pudo eliminar :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void onReset(){
		marcas = new ArrayList<Marca>();
		marcas.addAll(getMarcaService().getMarcas());
		filteredMarcas  = new ArrayList<Marca>();
		filteredMarcas.addAll(marcas);
	}

}
