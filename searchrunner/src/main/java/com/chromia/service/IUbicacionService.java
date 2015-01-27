package com.chromia.service;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

import java.util.List;

import com.chromia.model.Ubicacion;



/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

public interface IUbicacionService {
	
	public boolean addUbicacion(Ubicacion ubicacion);
	public boolean updateUbicacion(Ubicacion ubicacion);
	public boolean deleteUbicacion(Ubicacion ubicacion);
	public Ubicacion getUbicacionById(int id);
	public List<Ubicacion> getUbicaciones();
	
}
