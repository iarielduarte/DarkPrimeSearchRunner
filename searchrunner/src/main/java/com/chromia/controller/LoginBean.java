package com.chromia.controller;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.chromia.model.Usuario;
import com.chromia.service.IUsuarioService;
import com.chromia.service.UsuarioService;
import com.chromia.util.Util;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{UsuarioService}")
	IUsuarioService usuarioService;
	
	private Usuario usuario;
	private String nombre;
	private String clave;	
	
	

	//-- TODO: Constructor --//
    public LoginBean() {
		this.usuarioService = new UsuarioService();
		if(usuario == null){
			this.usuario = new Usuario();
		}
			
	}

	//-- TODO: Getter y setter del servicio --//
    public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
    public String getNombre() {
		return nombre;
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

	public IUsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
  
	//-- TODO: Acciones de la vista --//
    public void login(ActionEvent actionEvent) { 
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg ;  
        boolean loggedIn ;  
        String ruta = "";
        usuario.setNombre(this.getNombre());
        usuario.setClave(this.getClave());
        usuario = getUsuarioService().login(usuario);
          
        if(this.usuario != null ) {  
            loggedIn = true;  
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario.getNombre());
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido :)", usuario.getNombre());
            ruta = Util.basepathlogin()+"pages/inicio.xhtml";
        } else {  
            loggedIn = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Credenciales invalidas :( ", "El usuario o la clave son incorrectas");
            if(usuario == null){
            	this.usuario = new Usuario();
            }
    			
        }  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("ruta", ruta);
    }  
    
	public void logout() {
		String ruta = Util.basepathlogin() + "login.xhtml";
		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(false);
		sesion.invalidate();
		
		context.addCallbackParam("loggetOut", true);
		context.addCallbackParam("ruta", ruta);
	}
}
