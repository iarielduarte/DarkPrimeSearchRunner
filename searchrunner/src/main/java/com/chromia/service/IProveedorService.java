package com.chromia.service;

import java.util.List;

import com.chromia.model.Proveedor;

public interface IProveedorService {

	public boolean addProveedor(Proveedor proveedor);

	public boolean updateProveedor(Proveedor proveedor);

	public boolean deleteProveedor(Proveedor proveedor);

	public Proveedor getProveedorById(int id);

	public List<Proveedor> getProveedores();

	public List<Proveedor> getProveedoresByCiudad(int id);

	public List<Proveedor> getProveedoresByPais(int id);
}
