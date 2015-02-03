package com.chromia.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chromia.model.Categoria;

@Repository("CategoriaDao")
public class CategoriaDao implements ICategoria, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addCategoria(Categoria categoria) {
		getSessionFactory().getCurrentSession().save(categoria);

	}

	@Override
	public void updateCategoria(Categoria categoria) {
		getSessionFactory().getCurrentSession().update(categoria);

	}

	@Override
	public void deleteCategoria(Categoria categoria) {
		getSessionFactory().getCurrentSession().delete(categoria);

	}

	@Override
	public Categoria getCategoriaById(int id) {
		Query query = getSessionFactory().getCurrentSession()
				.getNamedQuery(Categoria.GET_CATEGORIA_BY_ID)
				.setInteger("id", id);
		return (Categoria) query.uniqueResult();
	}

	@Override
	public List<Categoria> getCategorias() {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery(
				Categoria.GET_ALL_CATEGORIAS);
		return (List<Categoria>) query.list();
	}

}
