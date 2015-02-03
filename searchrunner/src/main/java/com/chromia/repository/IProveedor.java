package com.chromia.repository;

import java.util.List;

import com.chromia.model.Proveedor;

public interface IProveedor {

	public void addProveedor(Proveedor proveedor);

	public void updateProveedor(Proveedor proveedor);

	public void deleteProveedor(Proveedor proveedor);

	public Proveedor getProveedorById(int id);

	public List<Proveedor> getProveedores();
}
