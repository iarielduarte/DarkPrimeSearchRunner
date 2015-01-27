package com.chromia.repository;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */
import java.util.List;

import com.chromia.model.Rol;

public interface IRolDao {

	public void addRol(Rol rol);
	public void updateRol(Rol rol);
	public void deleteRol(Rol rol);
	public Rol getRolById(int id);
	public List<Rol> getRols();
	
}
