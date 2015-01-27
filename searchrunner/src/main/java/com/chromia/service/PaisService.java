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

import com.chromia.model.Pais;
import com.chromia.repository.PaisDao;


@Service("PaisService")
@Transactional(readOnly = true)
public class PaisService implements IPaisService, Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PaisDao paisDao;
	
	
	public PaisDao getPaisDao() {
		return paisDao;
	}

	public void setPaisDao(PaisDao paisDao) {
		this.paisDao = paisDao;
	}

	@Transactional
	public boolean addPais(Pais pais) {
		boolean success = false;
		try {
			getPaisDao().addPais(pais);
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
	public boolean updatePais(Pais pais) {
		boolean success = false;
		try {
			getPaisDao().updatePais(pais);
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
	public boolean deletePais(Pais pais) {
		boolean success = false;
		try {
			getPaisDao().deletePais(pais);
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
	public Pais getPaisById(int id) {
		return getPaisDao().getPaisById(id);
	}

	@Transactional
	public List<Pais> getPaises() {
		return getPaisDao().getPaises();
	}


}
