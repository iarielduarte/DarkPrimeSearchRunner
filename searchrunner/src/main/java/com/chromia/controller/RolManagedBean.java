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
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.springframework.dao.DataAccessException;

import com.chromia.model.Rol;
import com.chromia.model.Usuario;
import com.chromia.service.IRolService;
import com.chromia.service.IUsuarioService;
import com.chromia.service.RolService;
import com.chromia.service.UsuarioService;

@ManagedBean(name = "rolMBean")
@ViewScoped
@SessionScoped
public class RolManagedBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{RolService}")
	private IRolService rolService;
	private String nombre;
	private List<Rol> roles;
	private List<Rol> filteredRoles;  
	private Rol selectedRol; 
	
	@PostConstruct
	public void inicializar() {
    	roles = getRolService().getRols();
		
	}
	
	/*TODO: View...Actions*/
	public void add(){
		Rol rol = new Rol();
		rol.setNombre(getNombre());
		getRolService().addRol(rol);
		
	}
	
	private List<SelectItem> selectOneItemRol;
	
	
	/*TODO: Getters...and...Setters*/
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}

	public IRolService getRolService() {
		return rolService;
	}

	public void setRolService(IRolService rolService) {
		this.rolService = rolService;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> rols) {
		this.roles = rols;
	}

	public List<SelectItem> getSelectOneItemRol() {
		selectOneItemRol = new ArrayList<SelectItem>();
		List<Rol> roles = getRolService().getRols();
		for (Rol rol : roles) {
			SelectItem selectItem = new SelectItem(rol.getId(), rol.getNombre());
			selectOneItemRol.add(selectItem);
		}
		return selectOneItemRol;
	}

	public Rol getSelectedRol() {
		if(selectedRol==null)
			selectedRol = getRolService().getRols().get(0); 
		return selectedRol;
	}

	public void setSelectedRol(Rol selectedRol) {
		this.selectedRol = selectedRol;
	}

	public List<Rol> getFilteredRoles() {
		return filteredRoles;
	}

	public void setFilteredRoles(List<Rol> filteredRoles) {
		this.filteredRoles = filteredRoles;
	}

//	TODO: Action Listener
	
	public void onCreate(ActionEvent actionEvent) {
		try {
			Rol rolAdd = new Rol();
			rolAdd.setNombre(getNombre());
			
		    if(getRolService().addRol(rolAdd))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "El Rol "+rolAdd.getNombre()+" se guardo con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "El Rol "+rolAdd.getNombre()+" no se pudo guardo :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión: ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	
	public void onEdit(ActionEvent actionEvent) {
		try {
			
		    if(getRolService().updateRol(getSelectedRol()))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "El Rol "+this.selectedRol.getNombre()+" se modifico con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "El Rol "+this.selectedRol.getNombre()+" no se pudo modificar :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void onDelete(ActionEvent actionEvent) {
	    try {
			
		    if(getRolService().deleteRol(getSelectedRol()))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "El Rol "+getSelectedRol().getNombre()+" fue eliminado con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "El Rol "+getSelectedRol().getNombre()+" no se pudo eliminar :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void onReset(){
		roles = new ArrayList<Rol>();
		roles.addAll(getRolService().getRols());
		filteredRoles  = new ArrayList<Rol>();
		filteredRoles.addAll(roles);
	}

}
