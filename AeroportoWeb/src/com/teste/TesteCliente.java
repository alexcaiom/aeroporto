/**
 * 
 */
package com.teste;

import com.dao.UsuarioDAO;
import com.model.usuario.Cliente;

/**
 * @author Alex
 *
 */
public class TesteCliente {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		inserirCliente();
	}

	protected static Cliente inserirCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome("Maria de Lurdes");
		cliente.setIdade(25);
		
		UsuarioDAO dao  = new UsuarioDAO();
		dao.inserir(cliente);
		return cliente;
	}

}
