/**
 * 
 */
package com.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Alex
 *
 */
@Entity
@Table(name = "tbl_assento")
public class Assento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -840514836942403254L;
	@Id @GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name="aviao")
	private Aviao aviao;
	
	private String nome;
	
	@Enumerated
	private TipoAssento tipo;
	@ManyToOne(cascade = CascadeType.DETACH)
	private Reserva reserva;
	
	public Assento() {
		// TODO Auto-generated constructor stub
	}
	public Assento(TipoAssento tipo, Reserva reserva) {
		this.tipo = tipo;
		this.reserva = reserva;
	}
	public Assento(Long id, TipoAssento tipo, Reserva reserva) {
		this.id = id;
		this.tipo = tipo;
		this.reserva = reserva;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Aviao getAviao() {
		return aviao;
	}
	public Assento setAviao(Aviao aviao) {
		this.aviao = aviao;
		return this;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public TipoAssento getTipo() {
		return tipo;
	}
	public void setTipo(TipoAssento tipo) {
		this.tipo = tipo;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	@Override
	public int hashCode() {
		return this.id.intValue();
	}
	
	@Override
	public String toString() {
		return this.id.toString();
	}
	
}
