package com.model.usuario;

import java.io.Serializable;

//@Entity
//@NamedQuery(name = "Person.findUserByIdWithDogs", query = "select p from Person p left join fetch p.dogs where p.id = :personId")
public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String FIND_USER_BY_ID_WITH_DOGS = "Person.findUserByIdWithDogs";
	
	protected int idade;
	protected String nome;

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}