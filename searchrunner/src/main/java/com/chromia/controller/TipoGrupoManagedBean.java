package com.chromia.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.springframework.dao.DataAccessException;

import com.chromia.model.Grupo;
import com.chromia.model.TipoGrupo;
import com.chromia.service.IGrupoService;
import com.chromia.service.ITipoGrupoService;

@ManagedBean(name = "tipoGrupoMBean")
@ViewScoped
public class TipoGrupoManagedBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<TipoGrupo> tipoGrupos;  
	  
    private TipoGrupo selectedTipoGrupo;  
    
    private List<TipoGrupo> filteredTipoGrupos;  
    
    private List<SelectItem> selectOneItemTipoGrupo;
  
    
   //-- Spring User Service is injected... --//
    @ManagedProperty(value = "#{GrupoService}")
	private IGrupoService grupoService;
 	@ManagedProperty(value = "#{TipoGrupoService}")
 	private ITipoGrupoService tipoGrupoService;
 	private String nombre;
	private Integer grupo;
 	
 	
 	//-- TODO: Constructor --//
    public TipoGrupoManagedBean() {
    	
	}
    
    @PostConstruct
	public void inicializar() {
    	onReset();
		
	}
    
    
  //-- TODO: Getter y setter del servicio --//
    public ITipoGrupoService getTipoGrupoService() {
		return tipoGrupoService;
	}

	public void setTipoGrupoService(ITipoGrupoService tipoGrupoService) {
		this.tipoGrupoService = tipoGrupoService;
	}

	public IGrupoService getGrupoService() {
		return grupoService;
	}

	public void setGrupoService(IGrupoService grupoService) {
		this.grupoService = grupoService;
	}

	public List<TipoGrupo> getTipoGrupos() {
		tipoGrupos = getTipoGrupoService().getTipoGrupos();
		return tipoGrupos;
	}

	public void setTipoGrupos(List<TipoGrupo> tipoGrupos) {
		this.tipoGrupos = tipoGrupos;
	}

	public TipoGrupo getSelectedTipoGrupo() {
		if(selectedTipoGrupo==null)
			selectedTipoGrupo = getTipoGrupoService().getTipoGrupos().get(0); 
		return selectedTipoGrupo;
	}

	public void setSelectedTipoGrupo(TipoGrupo selectedTipoGrupo) {
		this.selectedTipoGrupo = selectedTipoGrupo;
	}

	public List<TipoGrupo> getFilteredTipoGrupos() {
		return filteredTipoGrupos;
	}

	public void setFilteredTipoGrupos(List<TipoGrupo> filteredTipoGrupos) {
		this.filteredTipoGrupos = filteredTipoGrupos;
	}
	
	

	//-- TODO: Acciones de la vista --//

	public String getNombre() {
		return nombre;
	}

	public Integer getGrupo() {
		return grupo;
	}

	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<SelectItem> getSelectOneItemTipoGrupo() {
		selectOneItemTipoGrupo = new ArrayList<SelectItem>();
		List<TipoGrupo> tipoGrupos = getTipoGrupoService().getTipoGrupos();
		for (TipoGrupo tipoGrupo : tipoGrupos) {
			SelectItem selectItem = new SelectItem(tipoGrupo.getId(), tipoGrupo.getNombre());
			selectOneItemTipoGrupo.add(selectItem);
		}
		return selectOneItemTipoGrupo;
	}
	
//	TODO: Action Listener
	
	public void onCreate(ActionEvent actionEvent) {
		try {
			TipoGrupo tipoGrupoAdd = new TipoGrupo();
			tipoGrupoAdd.setNombre(getNombre());
			tipoGrupoAdd.setGrupo(getGrupoService().getGrupoById(getGrupo()));
			
		    if(getTipoGrupoService().addTipoGrupo(tipoGrupoAdd))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "El Tipo Grupo "+tipoGrupoAdd.getNombre()+" se guardo con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "El Tipo Grupo "+tipoGrupoAdd.getNombre()+" no se pudo guardo :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión: ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}
	
	
	public void onEdit(ActionEvent actionEvent) {
		try {
			
		    if(getTipoGrupoService().updateTipoGrupo(getSelectedTipoGrupo()))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "El Tipo Grupo "+this.selectedTipoGrupo.getNombre()+" se modifico con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "El Tipo Grupo "+this.selectedTipoGrupo.getNombre()+" no se pudo modificar :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	
	public void onDelete(ActionEvent actionEvent) {
	    try {
			
		    if(getTipoGrupoService().deleteTipoGrupo(getSelectedTipoGrupo()))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "El Tipo Grupo "+getSelectedTipoGrupo().getNombre()+" fue eliminado con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "El Tipo Grupo "+getSelectedTipoGrupo().getNombre()+" no se pudo eliminar :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
   
	public void onReset(){
		tipoGrupos = new ArrayList<TipoGrupo>();
		tipoGrupos.addAll(getTipoGrupoService().getTipoGrupos());
		filteredTipoGrupos  = new ArrayList<TipoGrupo>();
		filteredTipoGrupos.addAll(tipoGrupos);
	}

}
