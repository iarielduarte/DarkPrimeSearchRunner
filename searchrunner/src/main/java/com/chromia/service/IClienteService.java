package com.chromia.service;

import java.util.List;

import com.chromia.model.Cliente;

public interface IClienteService {

	public boolean addCliente(Cliente cliente);

	public boolean updateCliente(Cliente cliente);

	public boolean deleteCliente(Cliente cliente);

	public Cliente getClienteById(int id);

	public List<Cliente> getClientes();

	public List<Cliente> getClientesByCiudad(int id);

	public List<Cliente> getClientesByCategoria(int id);
}
