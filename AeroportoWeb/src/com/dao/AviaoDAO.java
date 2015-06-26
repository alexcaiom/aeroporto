package com.dao;
 
import com.model.Aviao;
 
public class AviaoDAO extends GenericDAO<Aviao> {
 
	private static final long serialVersionUID = 1L;

	public AviaoDAO() {
        super(Aviao.class);
    }
 
}