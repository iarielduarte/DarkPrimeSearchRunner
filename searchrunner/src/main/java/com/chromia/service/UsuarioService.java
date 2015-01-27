package com.chromia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chromia.model.Usuario;
import com.chromia.repository.UsuarioDao;


@Service("UsuarioService")
@Transactional(readOnly = true)
public class UsuarioService implements IUsuarioService {

	
	@Autowired
	private UsuarioDao usuarioDao;

	
	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	@Transactional
	public boolean addUsuario(Usuario usuario) {
		boolean success = false;
		try {
			getUsuarioDao().addUsuario(usuario);
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
	public boolean updateUsuario(Usuario usuario) {
		boolean success = false;
		try {
			getUsuarioDao().updateUsuario(usuario);
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
	public boolean deleteUsuario(Usuario usuario) {
		boolean success = false;
		try {
			getUsuarioDao().deleteUsuario(usuario);
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
	public Usuario getUsuarioById(int id) {
		return getUsuarioDao().getUsuarioById(id);
	}

	@Transactional
	public List<Usuario> getUsuarios() {
		return getUsuarioDao().getUsuarios();
	}

	@Transactional
	public Usuario findByUsuario(Usuario usuario) {
		return getUsuarioDao().findByUsuario(usuario);
	}

	@Transactional
	public Usuario login(Usuario usuario) {
		return getUsuarioDao().login(usuario);
	}

}
