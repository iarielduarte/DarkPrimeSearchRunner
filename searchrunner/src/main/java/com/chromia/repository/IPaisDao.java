package com.chromia.repository;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

import java.util.List;

import com.chromia.model.Pais;

public interface IPaisDao {

	public void addPais(Pais pais);
	public void updatePais(Pais pais);
	public void deletePais(Pais pais);
	public Pais getPaisById(int id);
	public List<Pais> getPaises();
	
}
