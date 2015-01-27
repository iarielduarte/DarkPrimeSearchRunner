package com.chromia.service;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chromia.model.Ubicacion;
import com.chromia.repository.UbicacionDao;



@Service("UbicacionService")
@Transactional(readOnly = true)
public class UbicacionService implements IUbicacionService, Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UbicacionDao ubicacionDao;
	
	
	public UbicacionDao getUbicacionDao() {
		return ubicacionDao;
	}

	public void setUbicacionDao(UbicacionDao ubicacionDao) {
		this.ubicacionDao = ubicacionDao;
	}

	@Transactional
	public boolean addUbicacion(Ubicacion ubicacion) {
		boolean success = false;
		try {
			getUbicacionDao().addUbicacion(ubicacion);
			success = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// log it and swallow exception
			// calling code has to be sure to check on success flag;
			// otherwise it has no idea something went terribly wrong
		}
		return success;

	}

	@Transactional
	public boolean updateUbicacion(Ubicacion ubicacion) {
		boolean success = false;
		try {
			getUbicacionDao().updateUbicacion(ubicacion);
			success = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// log it and swallow exception
			// calling code has to be sure to check on success flag;
			// otherwise it has no idea something went terribly wrong
		}
		return success;
	}

	@Transactional
	public boolean deleteUbicacion(Ubicacion ubicacion) {
		boolean success = false;
		try {
			getUbicacionDao().deleteUbicacion(ubicacion);
			success = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// log it and swallow exception
			// calling code has to be sure to check on success flag;
			// otherwise it has no idea something went terribly wrong
		}
		return success;

	}

	@Transactional
	public Ubicacion getUbicacionById(int id) {
		return getUbicacionDao().getUbicacionById(id);
	}

	@Transactional
	public List<Ubicacion> getUbicaciones() {
		return getUbicacionDao().getUbicaciones();
	}


}
