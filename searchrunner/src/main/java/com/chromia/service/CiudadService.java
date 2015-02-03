package com.chromia.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chromia.model.Ciudad;
import com.chromia.repository.CiudadDao;
import com.chromia.util.Util;

@Service("CiudadService")
@Transactional(readOnly = true)
public class CiudadService implements ICiudadService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CiudadDao ciudadDao;

	public void setCiudadDao(CiudadDao ciudadDao) {
		this.ciudadDao = ciudadDao;
	}

	public CiudadDao getCiudadDao() {
		return ciudadDao;
	}

	@Transactional
	public boolean addCiudad(Ciudad ciudad) {
		try {
			getCiudadDao().addCiudad(ciudad);
			return true;
		} catch (Exception e) {
			Util.logger(e.getMessage(), "error");
		}
		return false;
	}

	@Transactional
	public boolean updateCiudad(Ciudad ciudad) {
		try {
			getCiudadDao().updateCiudad(ciudad);
			return true;
		} catch (Exception e) {
			Util.logger(e.getMessage(), "error");
		}
		return false;
	}

	@Transactional
	public boolean deleteCiudad(Ciudad ciudad) {
		try {
			getCiudadDao().deleteCiudad(ciudad);
			return true;
		} catch (Exception e) {
			Util.logger(e.getMessage(), "error");
		}
		return false;
	}

	@Transactional
	public Ciudad getCiudadById(int id) {
		return getCiudadDao().getCiudadById(id);
	}

	@Transactional
	public List<Ciudad> getCiudades() {
		return getCiudadDao().getCiudades();
	}

}
