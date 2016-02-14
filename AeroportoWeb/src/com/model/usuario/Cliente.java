package com.model.usuario;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.model.Reserva;
import com.model.Role;

@Entity
@DiscriminatorValue("CLIENTE")
@Table(name="tbl_cliente")
@NamedQuery(name = "Person.findClienteByIdWithReservas", query = "select c from Cliente c left join fetch c.reservas where c.id = :clienteId")
public class Cliente extends Usuario 
					implements Serializable {

	public Cliente() {
		super(Role.CLIENTE);
	}

	private static final long serialVersionUID = 1L;
	public static final String FIND_USER_BY_ID_WITH_RESERVAS = "Person.findClienteByIdWithReservas";

	private Long cpf;
	@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER, mappedBy = "cliente")
	private List<Reserva> reservas;

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reserva) {
		this.reservas = reserva;
	}

	@Override
	public int hashCode() {
		return id.intValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Cliente) {
			Cliente person = (Cliente) obj;
			return person.getId() == id;
		}

		return false;
	}
}