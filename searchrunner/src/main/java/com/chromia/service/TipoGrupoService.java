package com.chromia.service;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chromia.model.TipoGrupo;
import com.chromia.repository.TipoGrupoDao;



@Service("TipoGrupoService")
@Transactional(readOnly = true)
public class TipoGrupoService implements ITipoGrupoService, Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TipoGrupoDao tipoGrupoDao;
	
	
	public TipoGrupoDao getTipoGrupoDao() {
		return tipoGrupoDao;
	}

	public void setTipoGrupoDao(TipoGrupoDao tipoGrupoDao) {
		this.tipoGrupoDao = tipoGrupoDao;
	}

	@Transactional
	public boolean addTipoGrupo(TipoGrupo tipoGrupo) {
		boolean success = false;
		try {
			getTipoGrupoDao().addTipoGrupo(tipoGrupo);
			success = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// log it and swallow exception
			// calling code has to be sure to check on success flag;
			// otherwise it has no idea something went terribly wrong
		}
		return success;

	}

	@Transactional
	public boolean updateTipoGrupo(TipoGrupo tipoGrupo) {
		boolean success = false;
		try {
			getTipoGrupoDao().updateTipoGrupo(tipoGrupo);
			success = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// log it and swallow exception
			// calling code has to be sure to check on success flag;
			// otherwise it has no idea something went terribly wrong
		}
		return success;
	}

	@Transactional
	public boolean deleteTipoGrupo(TipoGrupo tipoGrupo) {
		boolean success = false;
		try {
			getTipoGrupoDao().deleteTipoGrupo(tipoGrupo);
			success = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// log it and swallow exception
			// calling code has to be sure to check on success flag;
			// otherwise it has no idea something went terribly wrong
		}
		return success;

	}

	@Transactional
	public TipoGrupo getTipoGrupoById(int id) {
		return getTipoGrupoDao().getTipoGrupoById(id);
	}

	@Transactional
	public List<TipoGrupo> getTipoGrupos() {
		return getTipoGrupoDao().getTipoGrupos();
	}

	@Transactional
	public List<TipoGrupo> getTipoGruposByGrupo(int id) {
		return getTipoGrupoDao().getTipoGruposByGrupo(id);
	}


}
