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

import com.chromia.model.Pais;

@Repository("PaisDao")
public class PaisDao implements IPaisDao, Serializable {

	
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
	public void addPais(Pais pais) {
		getSessionFactory().getCurrentSession().save(pais);
		
	}

	@Override
	public void updatePais(Pais pais) {
		getSessionFactory().getCurrentSession().update(pais);
		
	}

	@Override
	public void deletePais(Pais pais) {
		getSessionFactory().getCurrentSession().delete(pais);
		
	}

	@Override
	public Pais getPaisById(int id) {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery(Pais.GET_PAIS_BY_ID).setInteger("id", id);
		return (Pais) query.uniqueResult();
	}

	@Override
	public List<Pais> getPaises() {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery(Pais.GET_ALL_PAISES);
		return (List<Pais>) query.list();
	}
	
	
}
