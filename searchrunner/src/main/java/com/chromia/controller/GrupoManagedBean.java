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

import com.chromia.model.Grupo;
import com.chromia.service.IGrupoService;

@ManagedBean(name = "grupoMBean")
@SessionScoped
@ViewScoped
public class GrupoManagedBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{GrupoService}")
	private IGrupoService grupoService;
	private String nombre;
	private List<Grupo> grupos;
	private List<Grupo> filteredGrupos;  
	private Grupo selectedGrupo; 
	
	@PostConstruct
	public void inicializar() {
    	grupos = getGrupoService().getGrupos();
		
	}
	
	private List<SelectItem> selectOneItemGrupo;
	
	
	/*TODO: Getters...and...Setters*/
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}

	public IGrupoService getGrupoService() {
		return grupoService;
	}

	public void setGrupoService(IGrupoService grupoService) {
		this.grupoService = grupoService;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<SelectItem> getSelectOneItemGrupo() {
		selectOneItemGrupo = new ArrayList<SelectItem>();
		List<Grupo> grupos = getGrupoService().getGrupos();
		for (Grupo grupo : grupos) {
			SelectItem selectItem = new SelectItem(grupo.getId(), grupo.getNombre());
			selectOneItemGrupo.add(selectItem);
		}
		return selectOneItemGrupo;
	}

	public Grupo getSelectedGrupo() {
		if(selectedGrupo==null)
			selectedGrupo = getGrupoService().getGrupos().get(0); 
		return selectedGrupo;
	}

	public void setSelectedGrupo(Grupo selectedGrupo) {
		this.selectedGrupo = selectedGrupo;
	}

	public List<Grupo> getFilteredGrupos() {
		return filteredGrupos;
	}

	public void setFilteredGrupos(List<Grupo> filteredGrupos) {
		this.filteredGrupos = filteredGrupos;
	}

//	TODO: Action Listener
	
	public void onCreate(ActionEvent actionEvent) {
		try {
			Grupo grupoAdd = new Grupo();
			grupoAdd.setNombre(getNombre());
			
		    if(getGrupoService().addGrupo(grupoAdd))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "El grupo "+grupoAdd.getNombre()+" se guardo con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "El grupo "+grupoAdd.getNombre()+" no se pudo guardo :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión: ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	
	public void onEdit(ActionEvent actionEvent) {
		try {
			
		    if(getGrupoService().updateGrupo(getSelectedGrupo()))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "El grupo "+this.selectedGrupo.getNombre()+" se modifico con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "El grupo "+this.selectedGrupo.getNombre()+" no se pudo modificar :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void onDelete(ActionEvent actionEvent) {
	    try {
			
		    if(getGrupoService().deleteGrupo(getSelectedGrupo()))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "El grupo "+getSelectedGrupo().getNombre()+" fue eliminado con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "El grupo "+getSelectedGrupo().getNombre()+" no se pudo eliminar :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void onReset(){
		grupos = new ArrayList<Grupo>();
		grupos.addAll(getGrupoService().getGrupos());
		filteredGrupos  = new ArrayList<Grupo>();
		filteredGrupos.addAll(grupos);
	}

}
