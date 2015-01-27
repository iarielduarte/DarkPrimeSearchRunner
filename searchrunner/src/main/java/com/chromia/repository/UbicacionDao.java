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

import com.chromia.model.Ubicacion;

@Repository("UbicacionDao")
public class UbicacionDao implements IUbicacionDao, Serializable {

	
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
	public void addUbicacion(Ubicacion ubicacion) {
		getSessionFactory().getCurrentSession().save(ubicacion);
		
	}

	@Override
	public void updateUbicacion(Ubicacion ubicacion) {
		getSessionFactory().getCurrentSession().update(ubicacion);
		
	}

	@Override
	public void deleteUbicacion(Ubicacion ubicacion) {
		getSessionFactory().getCurrentSession().delete(ubicacion);
		
	}

	@Override
	public Ubicacion getUbicacionById(int id) {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery(Ubicacion.GET_UBICACION_BY_ID).setInteger("id", id);
		return (Ubicacion) query.uniqueResult();
	}

	@Override
	public List<Ubicacion> getUbicaciones() {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery(Ubicacion.GET_ALL_UBICACIONES);
		return (List<Ubicacion>) query.list();
	}
	
	
}
