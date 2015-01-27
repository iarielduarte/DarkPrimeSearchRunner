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

import com.chromia.model.Marca;
import com.chromia.repository.MarcaDao;



@Service("MarcaService")
@Transactional(readOnly = true)
public class MarcaService implements IMarcaService, Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MarcaDao marcaDao;
	
	
	public MarcaDao getMarcaDao() {
		return marcaDao;
	}

	public void setMarcaDao(MarcaDao marcaDao) {
		this.marcaDao = marcaDao;
	}

	@Transactional
	public boolean addMarca(Marca marca) {
		boolean success = false;
		try {
			getMarcaDao().addMarca(marca);
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
	public boolean updateMarca(Marca marca) {
		boolean success = false;
		try {
			getMarcaDao().updateMarca(marca);
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
	public boolean deleteMarca(Marca marca) {
		boolean success = false;
		try {
			getMarcaDao().deleteMarca(marca);
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
	public Marca getMarcaById(int id) {
		return getMarcaDao().getMarcaById(id);
	}

	@Transactional
	public List<Marca> getMarcas() {
		return getMarcaDao().getMarcas();
	}


}
