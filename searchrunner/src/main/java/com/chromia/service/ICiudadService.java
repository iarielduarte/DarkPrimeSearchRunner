package com.chromia.service;

import java.util.List;

import com.chromia.model.Ciudad;

public interface ICiudadService {

	public boolean addCiudad(Ciudad ciudad);

	public boolean updateCiudad(Ciudad ciudad);

	public boolean deleteCiudad(Ciudad ciudad);

	public Ciudad getCiudadById(int id);

	public List<Ciudad> getCiudades();

}
