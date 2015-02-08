package com.chromia.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chromia.model.Proveedor;
import com.chromia.repository.ProveedorDao;
import com.chromia.util.Util;

@Service("ProveedorService")
@Transactional(readOnly = true)
public class ProveedorService implements IProveedorService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProveedorDao proveedorDao;

	public ProveedorDao getProveedorDao() {
		return proveedorDao;
	}

	public void setProveedorDao(ProveedorDao proveedorDao) {
		this.proveedorDao = proveedorDao;
	}

	@Transactional
	public boolean addProveedor(Proveedor proveedor) {
		try {
			getProveedorDao().addProveedor(proveedor);
			return true;
		} catch (Exception e) {
			Util.logger(e.getMessage(), "error");
		}
		return false;
	}

	@Transactional
	public boolean updateProveedor(Proveedor proveedor) {
		try {
			getProveedorDao().updateProveedor(proveedor);
			return true;
		} catch (Exception e) {
			Util.logger(e.getMessage(), "error");
		}
		return false;
	}

	@Transactional
	public boolean deleteProveedor(Proveedor proveedor) {
		try {
			getProveedorDao().deleteProveedor(proveedor);
			return true;
		} catch (Exception e) {
			Util.logger(e.getMessage(), "error");
		}
		return false;
	}

	@Transactional
	public Proveedor getProveedorById(int id) {
		return getProveedorDao().getProveedorById(id);
	}

	@Transactional
	public List<Proveedor> getProveedores() {
		return getProveedorDao().getProveedores();
	}

	@Transactional
	public List<Proveedor> getProveedoresByCiudad(int id) {
		return getProveedorDao().getProveedoresByCiudad(id);
	}

	@Transactional
	public List<Proveedor> getProveedoresByPais(int id) {
		return getProveedorDao().getProveedoresByPais(id);
	}

}
