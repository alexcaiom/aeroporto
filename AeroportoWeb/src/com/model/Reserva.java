package com.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.model.usuario.Cliente;

@Entity
@Table(name="tbl_reserva")
public class Reserva implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="voo")
	private Voo voo;
	
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente cliente;
	
	@OneToMany
	private List<Assento> assentos;
	@Transient
	private TipoAssento classe;
	private Float preco = 0f;
	
	public Reserva() {}
	
	public Reserva(Long id, Voo voo, List<Assento> assento) {
		this.id = id;
		this.voo = voo;
		this.assentos = assento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Voo getVoo() {
		return voo;
	}

	public void setVoo(Voo voo) {
		this.voo = voo;
	}

	public List<Assento>  getAssentos() {
		return assentos;
	}

	public void setAssentos(List<Assento>  assento) {
		this.assentos = assento;
	}

	public Float getPrecoFloat() {
		return preco;
	}
	
	public TipoAssento getClasseEnum(){
		return classe;
	}
	
	public String getClasse() {
		String retorno = "";
		if(classe != null){
			switch (classe) {
			case CLASSE_ECONOMICA:
				retorno = " R$ " + getVoo().getPrecoClasseEconomica();
				break;
			case CLASSE_EXECUTIVA:
				retorno = " R$ " + getVoo().getPrecoClasseExecutiva();
			default:
				break;
			}	
		}
		return retorno;
	}

	public void setClasse(TipoAssento classe) {
		this.classe = classe;
	}

	public String getPreco() {
		return preco.toString();
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
