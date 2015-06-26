package com.bo;

import java.io.Serializable;
import java.util.List;

import com.dao.AviaoDAO;
import com.model.Aviao;
import com.model.Voo;

public class AviaoBO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private AviaoDAO aviaoDAO = new AviaoDAO();

	public void inserir(Aviao aviao) {
		aviaoDAO.beginTransaction();
		aviaoDAO.inserir(aviao);
		aviaoDAO.commitAndCloseTransaction();
	}

	public void atualizar(Aviao aviao) {
		aviaoDAO.beginTransaction();
		Aviao persistedAviao = aviaoDAO.pesquisar(aviao.getId());
		persistedAviao.setNome(aviao.getNome());
		for(Voo voo: aviao.getVoos()){
			if(persistedAviao.getVoos().contains(voo)){
				persistedAviao.getVoos().add(voo);
			}
		}
		
		aviaoDAO.atualizar(persistedAviao);
		aviaoDAO.commitAndCloseTransaction();
	}

	public Aviao pesquisar(Long aviaoId) {
		aviaoDAO.beginTransaction();
		Aviao aviao = aviaoDAO.pesquisar(aviaoId);
		aviaoDAO.closeTransaction();
		return aviao;
	}

	public List<Aviao> listar() {
		aviaoDAO.beginTransaction();
		List<Aviao> result = aviaoDAO.listar();
		aviaoDAO.closeTransaction();
		return result;
	}

	public void excluir(Aviao aviao) {
		aviaoDAO.beginTransaction();
		Aviao persistedAviao = aviaoDAO.findReferenceOnly(aviao.getId());
		aviaoDAO.excluir(persistedAviao);
		aviaoDAO.commitAndCloseTransaction();
	}
}