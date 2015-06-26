/**
 * 
 */
package com.model.usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * @author Alex
 *
 */

@Entity
@Table(name = "tbl_endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JoinColumn(name="id_usuario")
	private Cliente cliente;
	private int CEP;
	private String logradouro;
	private int numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	
	public Endereco() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Endereco(Cliente cliente, int cEP, String logradouro, int numero,
			String complemento, String bairro, String cidade, String estado) {
		super();
		this.cliente = cliente;
		CEP = cEP;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return cliente;
	}
	public void setUsuario(Cliente cliente) {
		this.cliente = cliente;
	}
	public int getCEP() {
		return CEP;
	}
	public void setCEP(int cEP) {
		CEP = cEP;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
