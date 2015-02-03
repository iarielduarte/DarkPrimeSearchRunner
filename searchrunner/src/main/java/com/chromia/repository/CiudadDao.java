package com.chromia.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chromia.model.Ciudad;

@Repository("CiudadDao")
public class CiudadDao implements ICiudad, Serializable {

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
	public void addCiudad(Ciudad ciudad) {
		getSessionFactory().getCurrentSession().save(ciudad);

	}

	@Override
	public void updateCiudad(Ciudad ciudad) {
		getSessionFactory().getCurrentSession().update(ciudad);

	}

	@Override
	public void deleteCiudad(Ciudad ciudad) {
		getSessionFactory().getCurrentSession().delete(ciudad);

	}

	@Override
	public Ciudad getCiudadById(int id) {
		Query query = getSessionFactory().getCurrentSession()
				.getNamedQuery(Ciudad.GET_CIUDAD_BY_ID).setInteger("id", id);
		return (Ciudad) query.uniqueResult();
	}

	@Override
	public List<Ciudad> getCiudades() {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery(
				Ciudad.GET_ALL_CIUDADES);
		return (List<Ciudad>) query.list();
	}

}
