package com.chromia.service;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

import java.util.List;

import com.chromia.model.Marca;



/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

public interface IMarcaService {
	
	public boolean addMarca(Marca marca);
	public boolean updateMarca(Marca marca);
	public boolean deleteMarca(Marca marca);
	public Marca getMarcaById(int id);
	public List<Marca> getMarcas();
	
}
