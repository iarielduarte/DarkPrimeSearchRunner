package com.chromia.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;





import com.chromia.model.Rol;

@Repository("RolDao")
public class RolDao implements IRolDao, Serializable {

	
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
	public void addRol(Rol rol) {
		getSessionFactory().getCurrentSession().save(rol);
		
	}

	@Override
	public void updateRol(Rol rol) {
		getSessionFactory().getCurrentSession().update(rol);
		
	}

	@Override
	public void deleteRol(Rol rol) {
		getSessionFactory().getCurrentSession().delete(rol);
		
	}

	@Override
	public Rol getRolById(int id) {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery(Rol.GET_ROL_BY_ID).setInteger("id", id);
		return (Rol) query.uniqueResult();
	}

	@Override
	public List<Rol> getRols() {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery(Rol.GET_ALL_ROLS);
		return (List<Rol>) query.list();
	}
	
//	@Override
//	public Rol getRolById(int id) {
//		List list = getSessionFactory().getCurrentSession().createQuery("FROM Rol WHERE id=?").setParameter(0, id).list();
//		return (Rol) list.get(0);
//	}
//
//	@Override
//	public List<Rol> getRols() {
//		List list = getSessionFactory().getCurrentSession().createQuery("FROM Rol").list();
//		return list;
//	}
	
}
