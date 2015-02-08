package com.chromia.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chromia.model.Cliente;
import com.chromia.model.Proveedor;

@Repository("ProveedorDao")
public class ProveedorDao implements IProveedorDao, Serializable {

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
	public void addProveedor(Proveedor proveedor) {
		getSessionFactory().getCurrentSession().save(proveedor);

	}

	@Override
	public void updateProveedor(Proveedor proveedor) {
		getSessionFactory().getCurrentSession().update(proveedor);

	}

	@Override
	public void deleteProveedor(Proveedor proveedor) {
		getSessionFactory().getCurrentSession().delete(proveedor);

	}

	@Override
	public Proveedor getProveedorById(int id) {
		Query query = getSessionFactory().getCurrentSession()
				.getNamedQuery(Proveedor.GET_PROVEEDOR_BY_ID)
				.setInteger("id", id);
		return (Proveedor) query.uniqueResult();
	}

	@Override
	public List<Proveedor> getProveedores() {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery(
				Proveedor.GET_ALL_PROVEEDORES);
		return (List<Proveedor>) query.list();
	}

	@Override
	public List<Proveedor> getProveedoresByPais(int id) {
		Query query = getSessionFactory().getCurrentSession()
				.getNamedQuery(Proveedor.GET_PROVEEDOR_BY_PAIS_ID)
				.setInteger("id", id);
		return (List<Proveedor>) query.list();
	}

	@Override
	public List<Proveedor> getProveedoresByCiudad(int id) {
		Query query = getSessionFactory().getCurrentSession()
				.getNamedQuery(Proveedor.GET_PROVEEDOR_BY_CIUDAD_ID)
				.setInteger("id", id);
		return (List<Proveedor>) query.list();
	}

}
