package com.bo;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.List;

import com.dao.VooDAO;
import com.model.Reserva;
import com.model.Voo;

public class VooBO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private VooDAO vooDAO = new VooDAO();

	public void createVoo(Voo voo) {
		vooDAO.beginTransaction();
		vooDAO.inserir(voo);
		vooDAO.commitAndCloseTransaction();
	}

	public void updateVoo(Voo voo) {
//		try{
//			vooDAO.beginTransaction();
//			Voo persistedVoo = vooDAO.pesquisar(voo.getId());
//			persistedVoo.setDataSaida(voo.getDataSaidaCalendar());
//			persistedVoo.setDataChegada(voo.getDataChegadaCalendar());
//			persistedVoo.setAviao(voo.getAviao());
//
//			List<Reserva> reservasBD = persistedVoo.getReservas();
//
//			synchronized (reservasBD) {
//				for(Reserva reserva: reservasBD){
//					if(!voo.getReservas().contains(reserva)){
//						reservasBD.remove(reserva);
//					}
//				}
//
//				persistedVoo.setReservas(reservasBD);
//			}
//
//			for(Reserva reserva: voo.getReservas()){
//				if(!persistedVoo.getReservas().contains(reserva)){
//					persistedVoo.getReservas().add(reserva);
//				}
//			}
//
			vooDAO.atualizar(voo);
//			vooDAO.commit();
//		}catch (ConcurrentModificationException e){
//			e.printStackTrace();
//			vooDAO.rollback();
//		} finally{
//			vooDAO.closeTransaction();
//		}
	}

	public Voo pesquisar(Long vooId) {
		vooDAO.beginTransaction();
		Voo voo = vooDAO.pesquisar(vooId);
		vooDAO.closeTransaction();
		return voo;
	}

	public List<Voo> listar() {
		List<Voo> result = vooDAO.listar();
		return result;
	}

	public void deleteVoo(Voo voo) {
		vooDAO.beginTransaction();
		Voo persistedVoo = vooDAO.findReferenceOnly(voo.getId());
		vooDAO.excluir(persistedVoo);
		vooDAO.commitAndCloseTransaction();
	}
}