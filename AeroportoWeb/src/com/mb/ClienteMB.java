package com.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bo.ClienteBO;
import com.bo.ReservaBO;
import com.model.Reserva;
import com.model.usuario.Cliente;
import com.model.usuario.EstadoCivil;
import com.model.usuario.Genero;
import com.model.usuario.Usuario;
import com.sun.faces.context.flash.ELFlash;

@ViewScoped
@ManagedBean
public class ClienteMB extends LoginMB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final String SELECTED_CLIENTE = "selectedCliente";

	private Cliente cliente;
	private Reserva reserva;
	private Cliente clienteWithReservas;
	private Cliente clienteWithReservasForDetail;

	private List<Cliente> allClientes;
	private List<Reserva> reservas;

	private ReservaBO reservaFacade;
	private ClienteBO clienteFacade;
	
	private String passwordConfirmar;

	public void createCliente() {
		try {
			if(!passwordConfirmar.equals(getCliente().getPassword())){
				throw new Exception("As senhas não conferem!");
			}
			getClienteBO().inserir(getCliente());
			closeDialog();
			displayInfoMessageToUser("Created With Sucess");
			loadClientes();
			resetCliente();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public void updateCliente() {
		try {
			getClienteBO().atualizar(cliente);
			closeDialog();
			displayInfoMessageToUser("Updated With Sucess");
			loadClientes();
			resetCliente();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public void deleteCliente() {
		try {
			getClienteBO().excluir(cliente);
			closeDialog();
			displayInfoMessageToUser("Deleted With Sucess");
			loadClientes();
			resetCliente();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public void addReservaToCliente() {
		try {
			getClienteBO().addReservaToCliente(cliente.getId(), clienteWithReservas.getId());
			closeDialog();
			displayInfoMessageToUser("Added With Sucess");
			reloadClienteWithReservas();
			resetReserva();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public void removeReservaFromCliente() {
		try {
			getClienteBO().removeReservaFromCliente(cliente.getId(), clienteWithReservas.getId());
			closeDialog();
			displayInfoMessageToUser("Removed With Sucess");
			reloadClienteWithReservas();
			resetReserva();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public Cliente getClienteWithReservas() {
		Usuario  u = getUsuario();
		
		if(u instanceof Cliente){
			clienteWithReservas = getClienteBO().findClienteWithAllReservas(u.getId());
		}
		return clienteWithReservas;
	}

	public void setClienteWithReservasForDetail(Cliente cliente) {
		clienteWithReservasForDetail = getClienteBO().findClienteWithAllReservas(cliente.getId());
	}

	public Cliente getClienteWithReservasForDetail() {
		if (clienteWithReservasForDetail == null) {
			clienteWithReservasForDetail = new Cliente();
			clienteWithReservasForDetail.setReservas(new ArrayList<Reserva>());
		}

		return clienteWithReservasForDetail;
	}

	public void resetClienteWithReservasForDetail() {
		clienteWithReservasForDetail = new Cliente();
	}

	public String editClienteReservas() {
		ELFlash.getFlash().put(SELECTED_CLIENTE, cliente);
		return "/pages/protected/defaultUser/clienteReservas/clienteReservas.xhtml";
	}

	public List<Reserva> complete(String name) {
		List<Reserva> queryResult = new ArrayList<Reserva>();

		if (reservas == null) {
			reservaFacade = new ReservaBO();
			reservas = reservaFacade.listar();
		}

		reservas.removeAll(clienteWithReservas.getReservas());

		for (Reserva reserva : reservas) {
			if (reserva.getCliente().getNome().equals(name)) {
				queryResult.add(reserva);
			}
		}

		return queryResult;
	}

	public ClienteBO getClienteBO() {
		if (clienteFacade == null) {
			clienteFacade = new ClienteBO();
		}

		return clienteFacade;
	}

	public Cliente getCliente() {
		if (cliente == null) {
			cliente = new Cliente();
		}

		return cliente;
	}
	
	public void setCliente(Cliente cliente){
		this.cliente = cliente;
	}

	public List<Cliente> getAllClientes() {
		if (allClientes == null) {
			setAllClientes(loadClientes());
		}
		if(!allClientes.isEmpty()){
			cliente = allClientes.get(0);
		}
		return allClientes;
	}
	public void setAllClientes(List<Cliente> allClientes) {
		this.allClientes = allClientes;
	}

	private List<Cliente> loadClientes() {
		return allClientes = getClienteBO().listar();
	}
	public void resetCliente() {
		cliente = new Cliente();
	}

	public Reserva getReserva() {
		if (reserva == null) {
			reserva = new Reserva();
		}

		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public void resetReserva() {
		reserva = new Reserva();
	}

	private void reloadClienteWithReservas() {
		clienteWithReservas = getClienteBO().findClienteWithAllReservas(cliente.getId());
	}
	
	public List<Genero> getGeneros(){
		return Arrays.asList(Genero.values());
	}
	
	public List<EstadoCivil> getEstadosCivis(){
		return Arrays.asList(EstadoCivil.values());
	}

	public String getPasswordConfirmar() {
		return passwordConfirmar;
	}

	public void setPasswordConfirmar(String passwordConfirmar) {
		this.passwordConfirmar = passwordConfirmar;
	}
}