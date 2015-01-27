package com.chromia.controller;

/**
 * @author Ariel Duarte
 * 
 */
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.chromia.util.Util;


@ManagedBean(name = "appMBean")
@ApplicationScoped
public class AppManagedBean {

	
	public AppManagedBean() {
	
	}
	
	
	public String getBaseUrl(){
		return Util.baseurl();
	}
	

	public String getBasePath(){
		return Util.basepath();
	}
	
	public String getUrlSeguridadPage(){
		return Util.baseurl()+"faces/pages/seguridad/";
	}
	
	public String getUrlHome(){
		return  Util.baseurl()+"faces/pages/inicio.xhtml";
	}
	
	public String getUrlArticuloPage(){
		return Util.baseurl()+"faces/pages/articulo/";
	}
	
}
