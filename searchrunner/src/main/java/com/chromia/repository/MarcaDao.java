package com.chromia.repository;


/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */


import java.io.Serializable;
import java.util.List;



import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chromia.model.Grupo;
import com.chromia.model.Marca;

@Repository("MarcaDao")
public class MarcaDao implements IMarcaDao, Serializable {

	
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
	public void addMarca(Marca marca) {
		getSessionFactory().getCurrentSession().save(marca);
		
	}

	@Override
	public void updateMarca(Marca marca) {
		getSessionFactory().getCurrentSession().update(marca);
		
	}

	@Override
	public void deleteMarca(Marca marca) {
		getSessionFactory().getCurrentSession().delete(marca);
		
	}

	@Override
	public Marca getMarcaById(int id) {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery(Marca.GET_MARCA_BY_ID).setInteger("id", id);
		return (Marca) query.uniqueResult();
	}

	@Override
	public List<Marca> getMarcas() {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery(Marca.GET_ALL_MARCAS);
		return (List<Marca>) query.list();
	}
	
	
}
