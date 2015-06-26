package com.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.dao.AssentoDAO;
import com.model.Assento;
import com.model.Aviao;

public class AssentoBO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private AssentoDAO assentoDAO = new AssentoDAO();

	public void inserir(Assento assento) {
		assentoDAO.beginTransaction();
		assentoDAO.inserir(assento);
		assentoDAO.commitAndCloseTransaction();
	}

	public void atualizar(Aviao aviao) {
		assentoDAO.beginTransaction();
		Assento persistedAssento = assentoDAO.pesquisar(aviao.getId());
		assentoDAO.atualizar(persistedAssento);
		assentoDAO.commitAndCloseTransaction();
	}

	public List<Assento> pesquisar(List<String> idAssentos) {
		List<Assento> assentos = new ArrayList<Assento>();
		assentoDAO.beginTransaction();
		for(String id: idAssentos){
			Assento assento = assentoDAO.pesquisar(Long.parseLong(id));
			assentos.add(assento);
		}
		assentoDAO.closeTransaction();
		return assentos;
	}
	
	public Assento pesquisar(Long assentoId) {
		assentoDAO.beginTransaction();
		Assento assento = assentoDAO.pesquisar(assentoId);
		assentoDAO.closeTransaction();
		return assento;
	}

	public List<Assento> listar() {
		assentoDAO.beginTransaction();
		List<Assento> result = assentoDAO.listar();
		assentoDAO.closeTransaction();
		return result;
	}

	public void excluir(Assento assento) {
		assentoDAO.beginTransaction();
		Assento persistedAssento = assentoDAO.findReferenceOnly(assento.getId());
		assentoDAO.excluir(persistedAssento);
		assentoDAO.commitAndCloseTransaction();
	}
}