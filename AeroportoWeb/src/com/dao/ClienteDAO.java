package com.dao;
 
import java.util.HashMap;
import java.util.Map;

import com.model.usuario.Cliente;
 
public class ClienteDAO extends GenericDAO<Cliente> {
 
	private static final long serialVersionUID = 1L;

	public ClienteDAO() {
        super(Cliente.class);
    }
	
	public Cliente findClienteWithAllReservas(long clienteId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("clienteId", clienteId);

		return super.findOneResult(Cliente.FIND_USER_BY_ID_WITH_RESERVAS, parameters);
	}
}