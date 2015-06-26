/**
 * 
 */
package com.model.usuario;

import javax.faces.bean.ManagedBean;

/**
 * @author Alex
 *
 */
@ManagedBean(name="generoMB")
public enum Genero {
	MASCULINO("M","Masculino"),
	FEMININO("F","Feminino");
	
	String sigla; 
	String descricao;
	
	Genero(String sigla, String descricao) {
		this.sigla = sigla;
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
