package com.bo;

import java.util.List;

import com.dao.UsuarioDAO;
import com.model.usuario.Usuario;

public class UsuarioBO {
	private UsuarioDAO userDAO = new UsuarioDAO();

	public Usuario autenticar(String email, String password) {
		userDAO.beginTransaction();
		Usuario user = userDAO.findUserByEmail(email);

		if (user == null || !user.getPassword().equals(password)) {
			return null;
		}

		return user;
	}
	
	public void excluir(Usuario u){
		excluir(u.getId());
	}
	
	public void excluir(Long id){
		new UsuarioDAO().deleteUserById(id);
	}
	
	public void atualizar(Usuario u){
		new UsuarioDAO().atualizar(u);
	}

	public List<Usuario> listar() {
		return new UsuarioDAO().listar();
	}

	public Usuario pesquisar(Long id) {
		return new UsuarioDAO().pesquisar(id);
	}
	
	public void inserir(Usuario u){
		new UsuarioDAO().inserir(u);
	}
	
}