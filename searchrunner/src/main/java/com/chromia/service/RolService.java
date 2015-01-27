package com.chromia.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chromia.model.Rol;
import com.chromia.repository.RolDao;


@Service("RolService")
@Transactional(readOnly = true)
public class RolService implements IRolService, Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RolDao rolDao;
	
	
	public RolDao getRolDao() {
		return rolDao;
	}

	public void setRolDao(RolDao rolDao) {
		this.rolDao = rolDao;
	}

	@Transactional
	public boolean addRol(Rol rol) {
		boolean success = false;
		try {
			getRolDao().addRol(rol);
			success = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// log it and swallow exception
			// calling code has to be sure to check on success flag;
			// otherwise it has no idea something went terribly wrong
		}
		return success;

	}

	@Transactional
	public boolean updateRol(Rol rol) {
		boolean success = false;
		try {
			getRolDao().updateRol(rol);
			success = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// log it and swallow exception
			// calling code has to be sure to check on success flag;
			// otherwise it has no idea something went terribly wrong
		}
		return success;
	}

	@Transactional
	public boolean deleteRol(Rol rol) {
		boolean success = false;
		try {
			getRolDao().deleteRol(rol);
			success = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// log it and swallow exception
			// calling code has to be sure to check on success flag;
			// otherwise it has no idea something went terribly wrong
		}
		return success;

	}

	@Transactional
	public Rol getRolById(int id) {
		return getRolDao().getRolById(id);
	}

	@Transactional
	public List<Rol> getRols() {
		return getRolDao().getRols();
	}


}
