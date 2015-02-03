package com.chromia.repository;

import java.util.List;

import com.chromia.model.Cliente;

public interface ICliente {

	public void updateCliente(Cliente cliente);

	public void deleteCliente(Cliente cliente);

	public Cliente getClienteById(int id);

	public List<Cliente> getClientes();

	public void addCliente(Cliente cliente);

	public List<Cliente> getClientesByCategoria(int id);

	public List<Cliente> getClientesByCiudad(int id);
}
