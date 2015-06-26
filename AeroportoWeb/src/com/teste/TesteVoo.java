/**
 * 
 */
package com.teste;

import java.util.GregorianCalendar;

import com.dao.VooDAO;
import com.model.Voo;

/**
 * @author Alex
 *
 */
public class TesteVoo {

	public static void main(String[] args) {
		inserirVoo();
	}

	protected static Voo inserirVoo() {
		Voo voo = new Voo();
		voo.setDataSaida(GregorianCalendar.getInstance());
		voo.setDataChegada(GregorianCalendar.getInstance());
		voo.setLocalOrigem("São Paulo");
		voo.setLocalDestino("Rio de Janeiro");
		voo.setAviao(TesteAviao.inserirAviaoComAssentos(300));
		
		VooDAO dao = new VooDAO();
		dao.inserir(voo);
		return voo;
	}
	
	
}
