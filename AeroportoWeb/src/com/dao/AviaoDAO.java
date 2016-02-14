package com.dao;
 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.model.Assento;
import com.model.Aviao;
 
public class AviaoDAO extends GenericDAO<Aviao> {
 
	private static final long serialVersionUID = 1L;

	public AviaoDAO() {
        super(Aviao.class);
    }
	
	public List<Aviao> listar(){
		List<Aviao> lista = new ArrayList<>();
		beginTransaction();
		CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
		CriteriaQuery<Aviao> cq = criteriaBuilder.createQuery(Aviao.class);
		cq.select(cq.from(Aviao.class));
		lista = getEm().createQuery(cq).getResultList();
		for (Aviao obj : lista) {
			fetch(obj);
		}
		commitAndCloseTransaction();
		
		return lista;
	}
 
	public Aviao pesquisar(long id) {
		beginTransaction();
		Aviao aviao = getEm().find(Aviao.class, id);
		fetch(aviao);
		return aviao;
	}
	
	private void fetch (Aviao aviao) {
		for (Assento assento : aviao.getAssentos()) {
			assento.getNome();
		}
	}
}