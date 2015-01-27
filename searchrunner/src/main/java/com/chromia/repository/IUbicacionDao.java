package com.chromia.repository;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

import java.util.List;

import com.chromia.model.Ubicacion;

public interface IUbicacionDao {

	public void addUbicacion(Ubicacion ubicacion);
	public void updateUbicacion(Ubicacion ubicacion);
	public void deleteUbicacion(Ubicacion ubicacion);
	public Ubicacion getUbicacionById(int id);
	public List<Ubicacion> getUbicaciones();
	
}
