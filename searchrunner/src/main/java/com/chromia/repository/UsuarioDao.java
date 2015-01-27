package com.chromia.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chromia.model.Rol;
import com.chromia.model.Usuario;

@Repository("UsuarioDao")
public class UsuarioDao implements IUsuarioDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addUsuario(Usuario usuario) {
		getSessionFactory().getCurrentSession().save(usuario);

	}

	@Override
	public void updateUsuario(Usuario usuario) {
		getSessionFactory().getCurrentSession().update(usuario);

	}

	@Override
	public void deleteUsuario(Usuario usuario) {
		getSessionFactory().getCurrentSession().delete(usuario);

	}

	@Override
	public Usuario getUsuarioById(int id) {
		String query="SELECT u.id, u.nombre, u.email, u.clave, u.rol.id, u.rol.nombre " +
				"FROM Usuario u where id=?";
		List list = getSessionFactory().getCurrentSession().createQuery(query).setParameter(0, id).list();
		return (Usuario) list.get(0);
	}

	@Override
	public List<Usuario> getUsuarios() {
		String query="FROM Usuario u LEFT JOIN FETCH u.rol";
		List list = getSessionFactory().getCurrentSession().createQuery(query).list();
		return list;
	}

	@Override
	public Usuario findByUsuario(Usuario usuario) {
		Usuario query = null;
		String sql = "FROM Usuario WHERE nombre = '"+usuario.getNombre()+"'";
		try {
			query = (Usuario)getSessionFactory().getCurrentSession().createQuery(sql).uniqueResult();
		} catch (Exception e) {
			System.out.println("...................................");
			System.out.println(e.getMessage());
			System.out.println("...................................");
		}
		return query;
	}

	@Override
	public Usuario login(Usuario usuario) {
		Usuario usuarioLogin = this.findByUsuario(usuario);
		if(usuarioLogin != null){
			if(!usuario.getClave().equals(usuarioLogin.getClave())){
				usuarioLogin=null;
			}
		}
		return usuarioLogin;
	}

}
