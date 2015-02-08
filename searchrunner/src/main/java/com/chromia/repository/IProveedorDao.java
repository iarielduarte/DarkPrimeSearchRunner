package com.chromia.repository;

import java.util.List;

import com.chromia.model.Proveedor;

public interface IProveedorDao {

	public void addProveedor(Proveedor proveedor);

	public void updateProveedor(Proveedor proveedor);

	public void deleteProveedor(Proveedor proveedor);

	public Proveedor getProveedorById(int id);

	public List<Proveedor> getProveedores();

	public List<Proveedor> getProveedoresByPais(int id);

	public List<Proveedor> getProveedoresByCiudad(int id);
}
