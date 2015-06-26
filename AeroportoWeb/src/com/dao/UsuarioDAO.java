package com.dao;
 
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Query;
 






import com.model.Role;
import com.model.usuario.Administrador;
import com.model.usuario.Cliente;
import com.model.usuario.Operador;
import com.model.usuario.Usuario;
 
public class UsuarioDAO extends GenericDAO<Usuario> {
 
	private static final long serialVersionUID = 1L;

	public UsuarioDAO() {
        super(Usuario.class);
    }
 
    public Usuario findUserByEmail(String email){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("email", email);     
        
        Usuario usuario = super.findOneResult(Usuario.FIND_BY_EMAIL, parameters);
        if(usuario instanceof Administrador){
        	usuario.setRole(Role.ADMINISTRADOR);
        } else if(usuario instanceof Operador){
        	usuario.setRole(Role.OPERADOR);
        } else if(usuario instanceof Cliente){
        	usuario.setRole(Role.CLIENTE);
        } 
 
        return usuario;
    }
    
    public void deleteUserById(Long id){
    	beginTransaction();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", id);     
        
        Query q = getEm().createQuery("delete from Usuario u where u.id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
        commitAndCloseTransaction();
    }
}