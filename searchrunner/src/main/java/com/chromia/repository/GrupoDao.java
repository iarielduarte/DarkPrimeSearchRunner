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

@Repository("GrupoDao")
public class GrupoDao implements IGrupoDao, Serializable {

	
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
	public void addGrupo(Grupo grupo) {
		getSessionFactory().getCurrentSession().save(grupo);
		
	}

	@Override
	public void updateGrupo(Grupo grupo) {
		getSessionFactory().getCurrentSession().update(grupo);
		
	}

	@Override
	public void deleteGrupo(Grupo grupo) {
		getSessionFactory().getCurrentSession().delete(grupo);
		
	}

	@Override
	public Grupo getGrupoById(int id) {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery(Grupo.GET_GRUPO_BY_ID).setInteger("id", id);
		return (Grupo) query.uniqueResult();
	}

	@Override
	public List<Grupo> getGrupos() {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery(Grupo.GET_ALL_GRUPOS);
		return (List<Grupo>) query.list();
	}
	
	
}
