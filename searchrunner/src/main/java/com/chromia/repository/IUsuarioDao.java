package com.chromia.repository;

import java.util.List;

import com.chromia.model.Usuario;

public interface IUsuarioDao {

	public void addUsuario(Usuario usuario);
	public void updateUsuario(Usuario usuario);
	public void deleteUsuario(Usuario usuario);
	public Usuario getUsuarioById(int id);
	public List<Usuario> getUsuarios();
	public Usuario findByUsuario(Usuario usuario);
	public Usuario login(Usuario usuario);
}
