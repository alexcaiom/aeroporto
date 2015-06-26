package com.model.usuario;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.model.Role;

@Entity
@DiscriminatorValue("ADMINISTRADOR")
//@NamedQuery(name = "Person.findUserByIdWithDogs", query = "select p from Person p left join fetch p.dogs where p.id = :personId")
public class Administrador extends Usuario 
						implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String FIND_USER_BY_ID_WITH_DOGS = "Person.findUserByIdWithDogs";
	
	public Administrador() {
		super(Role.ADMINISTRADOR);
	}
	
	public Administrador(Role role, Long id, int idade, String nome) {
		super(role);
		this.id = id;
		this.idade = idade;
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return id.intValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Administrador) {
			Administrador person = (Administrador) obj;
			return person.getId() == id;
		}

		return false;
	}
}