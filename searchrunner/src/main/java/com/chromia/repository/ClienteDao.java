package com.chromia.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chromia.model.Cliente;

@Repository("ClienteDao")
public class ClienteDao implements ICliente, Serializable {

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
	public void addCliente(Cliente cliente) {
		getSessionFactory().getCurrentSession().save(cliente);

	}

	@Override
	public void updateCliente(Cliente cliente) {
		getSessionFactory().getCurrentSession().update(cliente);

	}

	@Override
	public void deleteCliente(Cliente cliente) {
		getSessionFactory().getCurrentSession().delete(cliente);

	}

	@Override
	public Cliente getClienteById(int id) {
		Query query = getSessionFactory().getCurrentSession()
				.getNamedQuery(Cliente.GET_CLIENTE_BY_ID).setInteger("id", id);
		return (Cliente) query.uniqueResult();
	}

	@Override
	public List<Cliente> getClientes() {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery(
				Cliente.GET_ALL_CLIENTES);
		return (List<Cliente>) query.list();
	}

	@Override
	public List<Cliente> getClientesByCategoria(int id) {
		Query query = getSessionFactory().getCurrentSession()
				.getNamedQuery(Cliente.GET_CLIENTE_BY_CATEGORIA_ID)
				.setInteger("id", id);
		return (List<Cliente>) query.list();
	}

	@Override
	public List<Cliente> getClientesByCiudad(int id) {
		Query query = getSessionFactory().getCurrentSession()
				.getNamedQuery(Cliente.GET_CLIENTE_BY_CIUDAD_ID)
				.setInteger("id", id);
		return (List<Cliente>) query.list();
	}

}
