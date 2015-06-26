/**
 * 
 */
package com.teste;

import javax.swing.JOptionPane;

import com.dao.AviaoDAO;
import com.model.Assento;
import com.model.Aviao;
import com.model.TipoAssento;

/**
 * @author Alex
 *
 */
public class TesteAviao {

	public static void main(String[] args) {
		inserirAviaoComAssentos(200);
	}

	protected static Aviao inserirAviaoComAssentos(int lugares) {
		String nomeAviao = JOptionPane.showInputDialog("Digite o nome do avi�o");
//		String nomeAviao = "Air France";
		
		Aviao aviao = new Aviao(nomeAviao);
		for(int cont = 0 ; cont < lugares; cont ++){
			aviao.addAssento(new Assento(TipoAssento.CLASSE_EXECUTIVA, null).setAviao(aviao));
		}
		AviaoDAO dao = new AviaoDAO();
		
		dao.inserir(aviao);
		return aviao;
	}
	
	
}
