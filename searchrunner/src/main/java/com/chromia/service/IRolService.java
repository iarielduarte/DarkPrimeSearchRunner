package com.chromia.service;

import java.util.List;

import com.chromia.model.Rol;


/**
 * 
 * User Service Interface
 * 
 * @author Ariel Duarte
 * @since 10 Oct 2013
 * @version 1.0.0
 *
 */
public interface IRolService {
	
	public boolean addRol(Rol rol);
	public boolean updateRol(Rol rol);
	public boolean deleteRol(Rol rol);
	public Rol getRolById(int id);
	public List<Rol> getRols();
	
}
