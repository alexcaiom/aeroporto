package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.util.FormatadorHora;

@Entity
@Table(name="tbl_voo")
public class Voo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String localOrigem;
	private String localDestino;
	private Calendar dataSaida;
	private Calendar dataChegada;
	
	@Column(nullable=false)
	private Float precoClasseEconomica;
	@Column(nullable=false)
	private Float precoClasseExecutiva;
	
	@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER, mappedBy = "voo")
	private List<Reserva> reservas;
	
	@ManyToOne
	@JoinColumn(name="aviao")
	private Aviao aviao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocalOrigem() {
		return localOrigem;
	}

	public void setLocalOrigem(String localOrigem) {
		this.localOrigem = localOrigem;
	}

	public String getLocalDestino() {
		return localDestino;
	}

	public void setLocalDestino(String localDestino) {
		this.localDestino = localDestino;
	}
	
	public Calendar getDataSaidaCalendar() {
		return dataSaida;
	}

	public String getDataSaida() {
		return FormatadorHora.formataDDMMAAAAHHMMSS(dataSaida);
	}

	public void setDataSaida(Calendar dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getDataChegada() {
		return FormatadorHora.formataDDMMAAAAHHMMSS(dataChegada);
	}
	public Calendar getDataChegadaCalendar() {
		return dataChegada;
	}

	public void setDataChegada(Calendar dataChegada) {
		this.dataChegada = dataChegada;
	}

	public Float getPrecoClasseEconomica() {
		return precoClasseEconomica;
	}

	public void setPrecoClasseEconomica(Float precoClasseEconomica) {
		this.precoClasseEconomica = precoClasseEconomica;
	}

	public Float getPrecoClasseExecutiva() {
		return precoClasseExecutiva;
	}

	public void setPrecoClasseExecutiva(Float precoClasseExecutiva) {
		this.precoClasseExecutiva = precoClasseExecutiva;
	}

	public List<Reserva> getReservas() {
		if(reservas == null){
			reservas = new ArrayList<Reserva>();
		}
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Aviao getAviao() {
		return aviao;
	}

	public void setAviao(Aviao aviao) {
		this.aviao = aviao;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Voo) {
			Voo voo = (Voo) obj;
			return voo.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return id.toString() + ") " + localOrigem + " -> " + localDestino + " com Saída " + getDataSaida() + " e Chegada " + getDataChegada();
	}
}