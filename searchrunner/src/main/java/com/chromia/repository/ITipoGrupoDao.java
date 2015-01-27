package com.chromia.repository;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

import java.util.List;

import com.chromia.model.TipoGrupo;

public interface ITipoGrupoDao {

	public void addTipoGrupo(TipoGrupo tipoGrupo);
	public void updateTipoGrupo(TipoGrupo tipoGrupo);
	public void deleteTipoGrupo(TipoGrupo tipoGrupo);
	public TipoGrupo getTipoGrupoById(int id);
	public List<TipoGrupo> getTipoGrupos();
	public List<TipoGrupo> getTipoGruposByGrupo(int id);
	
}
