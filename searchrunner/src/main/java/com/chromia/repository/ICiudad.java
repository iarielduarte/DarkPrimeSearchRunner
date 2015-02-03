package com.chromia.repository;

import java.util.List;

import com.chromia.model.Ciudad;

public interface ICiudad {

	public void addCiudad(Ciudad ciudad);

	public void updateCiudad(Ciudad ciudad);

	public void deleteCiudad(Ciudad ciudad);

	public Ciudad getCiudadById(int id);

	public List<Ciudad> getCiudades();

}
