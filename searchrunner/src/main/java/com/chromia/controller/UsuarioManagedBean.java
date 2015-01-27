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

import org.springframework.dao.DataAccessException;

import com.chromia.model.Usuario;
import com.chromia.service.IRolService;
import com.chromia.service.IUsuarioService;

@ManagedBean(name = "usuarioMBean")
@ViewScoped
@SessionScoped
public class UsuarioManagedBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<Usuario> usuarios;  
	  
    private Usuario selectedUsuario;  
    
    private List<Usuario> filteredUsuarios;  
  
    
   //-- Spring Service is injected... --//
    @ManagedProperty(value = "#{RolService}")
	private IRolService rolService;
 	@ManagedProperty(value = "#{UsuarioService}")
 	private IUsuarioService usuarioService;
 	private String nombre;
	private String clave;
	private String email;
	private Integer rol;
 	
 	
 	//-- TODO: Constructor --//
    public UsuarioManagedBean() {
    	
	}
    
    @PostConstruct
	public void inicializar() {
    	onReset();
		
	}
    
    
  //-- TODO: Getter y setter del servicio --//
    public IUsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public IRolService getRolService() {
		return rolService;
	}

	public void setRolService(IRolService rolService) {
		this.rolService = rolService;
	}

	public List<Usuario> getUsuarios() {
		usuarios = getUsuarioService().getUsuarios();
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getSelectedUsuario() {
		if(selectedUsuario==null)
			selectedUsuario = getUsuarioService().getUsuarios().get(0); 
		return selectedUsuario;
	}

	public void setSelectedUsuario(Usuario selectedUsuario) {
		this.selectedUsuario = selectedUsuario;
	}

	public List<Usuario> getFilteredUsuarios() {
		return filteredUsuarios;
	}

	public void setFilteredUsuarios(List<Usuario> filteredUsuarios) {
		this.filteredUsuarios = filteredUsuarios;
	}
	
	

	//-- TODO: Acciones de la vista --//

	public String getNombre() {
		return nombre;
	}

	public Integer getRol() {
		return rol;
	}

	public void setRol(Integer rol) {
		this.rol = rol;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
//	TODO: Action Listener
	
	public void onCreate(ActionEvent actionEvent) {
		try {
			Usuario userAdd = new Usuario();
			userAdd.setNombre(getNombre());
			userAdd.setClave(getClave());
			userAdd.setEmail(getEmail());
			userAdd.setRol(getRolService().getRolById(getRol()));
			
		    if(getUsuarioService().addUsuario(userAdd))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "El usuario "+userAdd.getNombre()+" se guardo con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "El usuario "+userAdd.getNombre()+" no se pudo guardo :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión: ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}
	
	
	public void onEdit(ActionEvent actionEvent) {
		try {
			
		    if(getUsuarioService().updateUsuario(getSelectedUsuario()))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "El usuario "+this.selectedUsuario.getNombre()+" se modifico con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "El usuario "+this.selectedUsuario.getNombre()+" no se pudo modificar :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	
	public void onDelete(ActionEvent actionEvent) {
	    try {
			
		    if(getUsuarioService().deleteUsuario(getSelectedUsuario()))
		    {
		    	onReset();
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito : ",  "El usuario "+getSelectedUsuario().getNombre()+" fue eliminado con éxito :)");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }else{
		    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",  "El usuario "+getSelectedUsuario().getNombre()+" no se pudo eliminar :(");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",  "Error en el acceso a la base de datos, Detalle: "+e.getMessage()+" x(");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
   
	public void onReset(){
		usuarios = new ArrayList<Usuario>();
		usuarios.addAll(getUsuarioService().getUsuarios());
		filteredUsuarios  = new ArrayList<Usuario>();
		filteredUsuarios.addAll(usuarios);
	}

}
