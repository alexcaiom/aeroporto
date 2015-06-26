package com.bo;

import java.io.Serializable;
import java.util.List;

import com.dao.ClienteDAO;
import com.dao.ReservaDAO;
import com.model.Reserva;
import com.model.usuario.Cliente;
import com.model.usuario.Usuario;

public class ClienteBO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ClienteDAO clienteDAO = new ClienteDAO();
	private ReservaDAO reservaDAO = new ReservaDAO();

	public void inserir(Cliente cliente) {
		clienteDAO.inserir(cliente);
	}

	public void atualizar(Cliente cliente) {
		Cliente persistedCliente = clienteDAO.pesquisar(cliente.getId());
		persistedCliente.setNome(cliente.getNome());
		persistedCliente.setIdade(cliente.getIdade());
		clienteDAO.atualizar(persistedCliente);
	}
	
	public void excluir(Cliente cliente){
		Cliente persistedPersonWithIdOnly = clienteDAO.findReferenceOnly(cliente.getId());
		clienteDAO.excluir(persistedPersonWithIdOnly);
	}

	public Usuario pesquisar(Long clienteId) {
		Cliente cliente = clienteDAO.pesquisar(clienteId);
		return cliente;
	}

	public List<Cliente> listar() {
		List<Cliente> result = clienteDAO.listar();
		return result;
	}

	public Cliente findClienteWithAllReservas(long reservaId) {
		Cliente cliente = clienteDAO.findClienteWithAllReservas(reservaId);
		return cliente;
	}

	public void addReservaToCliente(long reservaId, long clienteId) {
		clienteDAO.beginTransaction();
		reservaDAO.joinTransaction();
		Reserva reserva = reservaDAO.pesquisar(new Long(reservaId));
		Cliente cliente = clienteDAO.pesquisar(new Long(clienteId));
		cliente.getReservas().add(reserva);
		reserva.setCliente(cliente);
		clienteDAO.commitAndCloseTransaction();
	}

	public void removeReservaFromCliente(long reservaId, long clienteId) {
		clienteDAO.beginTransaction();
		reservaDAO.joinTransaction();
		Reserva reserva = reservaDAO.pesquisar(reservaId);
		Cliente cliente = clienteDAO.pesquisar(clienteId);
		cliente.getReservas().remove(reserva);
		reserva.setCliente(cliente);
		clienteDAO.commitAndCloseTransaction();
	}
}