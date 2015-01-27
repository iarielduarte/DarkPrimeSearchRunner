package com.chromia.service;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

import java.util.List;

import com.chromia.model.TipoGrupo;



/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

public interface ITipoGrupoService {
	
	public boolean addTipoGrupo(TipoGrupo tipoGrupo);
	public boolean updateTipoGrupo(TipoGrupo tipoGrupo);
	public boolean deleteTipoGrupo(TipoGrupo tipoGrupo);
	public TipoGrupo getTipoGrupoById(int id);
	public List<TipoGrupo> getTipoGrupos();
	public List<TipoGrupo> getTipoGruposByGrupo(int id);
	
}
