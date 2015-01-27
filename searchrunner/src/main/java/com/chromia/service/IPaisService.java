package com.chromia.service;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

import java.util.List;

import com.chromia.model.Pais;


/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

public interface IPaisService {
	
	public boolean addPais(Pais pais);
	public boolean updatePais(Pais pais);
	public boolean deletePais(Pais pais);
	public Pais getPaisById(int id);
	public List<Pais> getPaises();
	
}
