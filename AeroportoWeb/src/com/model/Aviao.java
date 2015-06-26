package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbl_aviao")
public class Aviao implements Serializable{
	private static final long serialVersionUID = -460060231048434152L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private Operadora operadora;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "aviao")
	private List<Assento> assentos;
	@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "aviao")
	private List<Voo> voos;
	
	
	public Aviao() {}
	
	public Aviao(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Operadora getOperadora() {
		return operadora;
	}

	public void setOperadora(Operadora operadora) {
		this.operadora = operadora;
	}

	public List<Assento> getAssentos() {
		if(assentos == null){
			assentos = new ArrayList<Assento>();
		}
		return assentos;
	}

	public void setAssentos(List<Assento> assentos) {
		this.assentos = assentos;
	}
	
	public void addAssento(Assento assento){
		getAssentos().add(assento);
	}

	public List<Voo> getVoos() {
		return voos;
	}

	public void setVoos(List<Voo> voos) {
		this.voos = voos;
	}

	/*public List<Assento> getAssentos() {
		return assentos;
	}

	public void setAssentos(List<Assento> assentos) {
		this.assentos = assentos;
	}
*/
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Aviao) {
			Aviao aviao = (Aviao) obj;
			return aviao.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return id.toString();
	}
}
