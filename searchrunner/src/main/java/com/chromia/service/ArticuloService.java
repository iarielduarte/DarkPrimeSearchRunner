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

import com.chromia.model.Articulo;
import com.chromia.repository.ArticuloDao;


@Service("ArticuloService")
@Transactional(readOnly = true)
public class ArticuloService implements IArticuloService, Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ArticuloDao articuloDao;
	
	
	public ArticuloDao getArticuloDao() {
		return articuloDao;
	}

	public void setArticuloDao(ArticuloDao articuloDao) {
		this.articuloDao = articuloDao;
	}

	@Transactional
	public boolean addArticulo(Articulo articulo) {
		boolean success = false;
		try {
			getArticuloDao().addArticulo(articulo);
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
	public boolean updateArticulo(Articulo articulo) {
		boolean success = false;
		try {
			getArticuloDao().updateArticulo(articulo);
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
	public boolean deleteArticulo(Articulo articulo) {
		boolean success = false;
		try {
			getArticuloDao().deleteArticulo(articulo);
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
	public Articulo getArticuloById(int id) {
		return getArticuloDao().getArticuloById(id);
	}

	@Transactional
	public List<Articulo> getArticulos() {
		return getArticuloDao().getArticulos();
	}

	@Transactional
	public Integer getMaxId() {
		return getArticuloDao().getMaxId();
	}

	@Transactional
	public Articulo getArticuloByName(String name) {
		return getArticuloDao().getArticuloByName(name);
	}


}
