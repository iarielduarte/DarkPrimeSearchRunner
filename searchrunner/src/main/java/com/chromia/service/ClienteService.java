package com.chromia.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chromia.model.Cliente;
import com.chromia.repository.ClienteDao;
import com.chromia.util.Util;

@Service("ClienteService")
@Transactional(readOnly = true)
public class ClienteService implements IClienteService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ClienteDao clienteDao;

	public ClienteDao getClienteDao() {
		return clienteDao;
	}

	public void setClienteDao(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}

	@Transactional
	public boolean addCliente(Cliente cliente) {
		try {
			getClienteDao().addCliente(cliente);
			return true;
		} catch (Exception e) {
			Util.logger(e.getMessage(), "error");
		}
		return false;
	}

	@Transactional
	public boolean updateCliente(Cliente cliente) {
		try {
			getClienteDao().updateCliente(cliente);
			return true;
		} catch (Exception e) {
			Util.logger(e.getMessage(), "error");
		}
		return false;
	}

	@Transactional
	public boolean deleteCliente(Cliente cliente) {
		try {
			getClienteDao().deleteCliente(cliente);
			return true;
		} catch (Exception e) {
			Util.logger(e.getMessage(), "error");
		}
		return false;
	}

	@Transactional
	public Cliente getClienteById(int id) {
		return getClienteDao().getClienteById(id);
	}

	@Transactional
	public List<Cliente> getClientes() {
		return getClienteDao().getClientes();
	}

	@Override
	public List<Cliente> getClientesByCiudad(int id) {
		return getClienteDao().getClientesByCiudad(id);
	}

	@Override
	public List<Cliente> getClientesByCategoria(int id) {
		return getClienteDao().getClientesByCategoria(id);
	}

}
