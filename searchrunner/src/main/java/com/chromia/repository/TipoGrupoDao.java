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

import com.chromia.model.TipoGrupo;
import com.chromia.model.Usuario;

@Repository("TipoGrupoDao")
public class TipoGrupoDao implements ITipoGrupoDao, Serializable {

	
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
	public void addTipoGrupo(TipoGrupo tipoGrupo) {
		getSessionFactory().getCurrentSession().save(tipoGrupo);
		
	}

	@Override
	public void updateTipoGrupo(TipoGrupo tipoGrupo) {
		getSessionFactory().getCurrentSession().update(tipoGrupo);
		
	}

	@Override
	public void deleteTipoGrupo(TipoGrupo tipoGrupo) {
		getSessionFactory().getCurrentSession().delete(tipoGrupo);
		
	}

	@Override
	public TipoGrupo getTipoGrupoById(int id) {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery(TipoGrupo.GET_TIPO_GRUPO_BY_ID).setInteger("id", id);
		return (TipoGrupo) query.uniqueResult();
	}

	@Override
	public List<TipoGrupo> getTipoGrupos() {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery(TipoGrupo.GET_ALL_TIPO_GRUPOS);
		return (List<TipoGrupo>) query.list();
	}

	@Override
	public List<TipoGrupo> getTipoGruposByGrupo(int id) {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery(TipoGrupo.GET_TIPO_GRUPO_BY_GRUPO_ID).setInteger("id", id);
		return (List<TipoGrupo>) query.list();
	}
	
	
//	@Override
//	public List<TipoGrupo> getTipoGrupos() {
//		String query="FROM TipoGrupo t LEFT JOIN FETCH t.grupo";
//		List list = getSessionFactory().getCurrentSession().createQuery(query).list();
//		return list;
//	}
	
}
