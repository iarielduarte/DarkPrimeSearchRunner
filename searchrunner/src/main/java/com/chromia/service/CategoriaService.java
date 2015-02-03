package com.chromia.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chromia.model.Categoria;
import com.chromia.repository.CategoriaDao;
import com.chromia.util.Util;

@Service("CategoriaService")
@Transactional(readOnly = true)
public class CategoriaService implements ICategoriaService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CategoriaDao categoriaDao;

	public CategoriaDao getCategoriaDao() {
		return categoriaDao;
	}

	public void setCategoriaDao(CategoriaDao categoriaDao) {
		this.categoriaDao = categoriaDao;
	}

	@Transactional
	public boolean addCategoria(Categoria categoria) {
		try {
			getCategoriaDao().addCategoria(categoria);
			return true;
		} catch (Exception e) {
			Util.logger(e.getMessage(), "error");
		}
		return false;
	}

	@Transactional
	public boolean updateCategoria(Categoria categoria) {
		try {
			getCategoriaDao().updateCategoria(categoria);
			return true;
		} catch (Exception e) {
			Util.logger(e.getMessage(), "error");
		}
		return false;
	}

	@Transactional
	public boolean deleteCategoria(Categoria categoria) {
		try {
			getCategoriaDao().deleteCategoria(categoria);
			return true;
		} catch (Exception e) {
			Util.logger(e.getMessage(), "error");
		}
		return false;
	}

	@Transactional
	public Categoria getCategoriaById(int id) {
		return getCategoriaDao().getCategoriaById(id);
	}

	@Transactional
	public List<Categoria> getCategorias() {
		return getCategoriaDao().getCategorias();
	}

}
