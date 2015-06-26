/**
 * 
 */
package com.teste;

import com.dao.ReservaDAO;
import com.model.Reserva;
import com.model.Voo;

/**
 * @author Alex
 *
 */
public class TesteReserva {

	public static void main(String[] args) {
		inserirReserva();
	}

	protected static Reserva inserirReserva() {
		Reserva reserva = new Reserva();
		Voo voo  = TesteVoo.inserirVoo();
		
		for(int cont = 0; cont < voo.getAviao().getAssentos().size(); cont ++){
			voo.getAviao().getAssentos().get(cont).setReserva(reserva);
		}
		
		reserva.setCliente(TesteCliente.inserirCliente());
		
		reserva.setVoo(voo);
		
		ReservaDAO dao = new ReservaDAO();
		dao.inserir(reserva);
		return reserva;
	}
	
	
}
