package com.chromia.repository;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

import java.util.List;

import com.chromia.model.Grupo;

public interface IGrupoDao {

	public void addGrupo(Grupo grupo);
	public void updateGrupo(Grupo grupo);
	public void deleteGrupo(Grupo grupo);
	public Grupo getGrupoById(int id);
	public List<Grupo> getGrupos();
	
}
