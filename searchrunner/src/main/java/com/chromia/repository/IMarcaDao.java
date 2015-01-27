package com.chromia.repository;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

import java.util.List;

import com.chromia.model.Marca;

public interface IMarcaDao {

	public void addMarca(Marca marca);
	public void updateMarca(Marca marca);
	public void deleteMarca(Marca marca);
	public Marca getMarcaById(int id);
	public List<Marca> getMarcas();
	
}
