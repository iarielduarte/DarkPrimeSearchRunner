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

import com.chromia.model.Grupo;
import com.chromia.repository.GrupoDao;


@Service("GrupoService")
@Transactional(readOnly = true)
public class GrupoService implements IGrupoService, Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private GrupoDao grupoDao;
	
	
	public GrupoDao getGrupoDao() {
		return grupoDao;
	}

	public void setGrupoDao(GrupoDao grupoDao) {
		this.grupoDao = grupoDao;
	}

	@Transactional
	public boolean addGrupo(Grupo grupo) {
		boolean success = false;
		try {
			getGrupoDao().addGrupo(grupo);
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
	public boolean updateGrupo(Grupo grupo) {
		boolean success = false;
		try {
			getGrupoDao().updateGrupo(grupo);
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
	public boolean deleteGrupo(Grupo grupo) {
		boolean success = false;
		try {
			getGrupoDao().deleteGrupo(grupo);
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
	public Grupo getGrupoById(int id) {
		return getGrupoDao().getGrupoById(id);
	}

	@Transactional
	public List<Grupo> getGrupos() {
		return getGrupoDao().getGrupos();
	}


}
