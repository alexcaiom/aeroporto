package com.bo;

import java.io.Serializable;
import java.util.List;

import com.dao.AssentoDAO;
import com.dao.ReservaDAO;
import com.model.Assento;
import com.model.Reserva;

public class ReservaBO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ReservaDAO reservaDAO = new ReservaDAO();

	public void inserir(Reserva reserva) {
		reservaDAO.inserir(reserva);
		reservaDAO.beginTransaction();
		if(reserva.getAssentos() != null){
			AssentoDAO assentoDAO = new AssentoDAO();
			for(Assento a: reserva.getAssentos()){
				a.setReserva(reserva);
				assentoDAO.atualizar(a);
			}
		}
		reservaDAO.commitAndCloseTransaction();
	}

	public void atualizar(Reserva reserva) {
		reservaDAO.beginTransaction();
		Reserva persistedReserva = reservaDAO.pesquisar(reserva.getId());
		persistedReserva.setVoo(reserva.getVoo());
		persistedReserva.setCliente(reserva.getCliente());
		
		for(Assento assento: persistedReserva.getAssentos()){
			if(!reserva.getAssentos().contains(assento)){
				persistedReserva.getAssentos().remove(assento);
			}
		}
		
		for(Assento assento: persistedReserva.getAssentos()){
			if(!persistedReserva.getAssentos().contains(assento)){
				persistedReserva.getAssentos().add(assento);
			}
		}
		
		reservaDAO.atualizar(persistedReserva);
		reservaDAO.commitAndCloseTransaction();
	}

	public Reserva pesquisar(Long reservaId) {
		reservaDAO.beginTransaction();
		Reserva reserva = reservaDAO.pesquisar(reservaId);
		reservaDAO.closeTransaction();
		return reserva;
	}
	
	public List<Reserva> listar() {
		List<Reserva> result = reservaDAO.listar();
		return result;
	}

	public void excluir(Reserva reserva) {
		reservaDAO.beginTransaction();
		Reserva persistedReserva = reservaDAO.findReferenceOnly(reserva.getId());
		reservaDAO.excluir(persistedReserva);
		reservaDAO.commitAndCloseTransaction();
	}

}