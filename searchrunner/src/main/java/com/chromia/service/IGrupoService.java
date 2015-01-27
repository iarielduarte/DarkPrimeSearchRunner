package com.chromia.service;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

import java.util.List;

import com.chromia.model.Grupo;


/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

public interface IGrupoService {
	
	public boolean addGrupo(Grupo grupo);
	public boolean updateGrupo(Grupo grupo);
	public boolean deleteGrupo(Grupo grupo);
	public Grupo getGrupoById(int id);
	public List<Grupo> getGrupos();
	
}
