package com.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.ToggleEvent;

import com.bo.AssentoBO;
import com.bo.ReservaBO;
import com.bo.VooBO;
import com.excecoes.ExcecaoNegocio;
import com.model.Assento;
import com.model.Reserva;
import com.model.TipoAssento;
import com.model.Voo;
import com.model.usuario.Cliente;

@ViewScoped
@ManagedBean
public class ClienteReservaMB extends AbstractMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idVooSelecionado;
	private Voo vooSelecionado;
	private List<Voo> voos;
	private Integer qtdAdultos = 0;
	private Integer qtdCriancas = 0;
	private Integer qtdBebesColo = 0;
	private Integer qtdTotal = 0;
	private Calendar dataSaida;
	private Calendar dataChegada;
	private String localOrigem;
	private String localDestino;
	private Reserva reserva = new Reserva();
	private List<Assento> assentos;
	private List<String> idAssentosEscolhidos = new ArrayList<String>();
	private List<Assento> assentosEscolhidos;
	private ReservaBO reservaBO;
	private List<Reserva> reservas;
	private Cliente cliente;
	
	private Integer classe = 0;

	public void createReserva() {
		try {
			validaReserva();
			
			List<Assento> assentosReserva = new ArrayList<Assento>();
			assentosReserva = new AssentoBO().pesquisar(idAssentosEscolhidos);
			
			reserva.setAssentos(assentosReserva);
			reserva.setCliente(getCliente());
			getReservaBO().inserir(reserva);
			closeDialog();
			displayInfoMessageToUser("Created With Sucess");
			loadReservas();
			resetVoo();
		} catch (ExcecaoNegocio e) {
			keepDialogOpen();
			displayErrorMessageToUser(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}
	
	private void validaReserva() throws Exception {
		if(vooSelecionado == null){
			throw new ExcecaoNegocio("Selecione um Vôo.");
		}
		if(qtdAdultos == 0){
			throw new ExcecaoNegocio("Ao menos um adulto deve estar escalado.");
		}
		if(classe == null){
			throw new ExcecaoNegocio("Selecione uma classe");
		}
		if(idAssentosEscolhidos.isEmpty()){
			throw new ExcecaoNegocio("Selecione seus assentos");
		} else if (idAssentosEscolhidos.size() > qtdTotal){
			throw new ExcecaoNegocio("Aumente o número de pessoas para selecionar mais assentos");
		} else if (idAssentosEscolhidos.size() < qtdTotal){
			throw new ExcecaoNegocio("Selecione mais " + (qtdTotal - idAssentosEscolhidos.size()) + " assentos");
		}
	}

	public void updateReserva() {
		try {
			getReservaBO().atualizar(reserva);
			closeDialog();
			displayInfoMessageToUser("Updated With Sucess");
			loadVoos();
			resetVoo();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}
	
	public void deleteReserva() {
		try {
			getReservaBO().excluir(reserva);
			closeDialog();
			displayInfoMessageToUser("Deleted With Sucess");
			loadVoos();
			resetVoo();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public List<Voo> getVoos() {
		if (voos == null) {
			loadVoos();
		}

		return voos;
	}

	private void loadVoos() {
		voos = new VooBO().listar();
	}
	
	private void loadReservas() {
		reservas = getCliente().getReservas();
	}

	public void resetVoo() {
		vooSelecionado = new Voo();
	}
	
	public void resetReserva() {
		reserva = new Reserva();
	}

	public Calendar getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Calendar dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Calendar getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(Calendar dataChegada) {
		this.dataChegada = dataChegada;
	}

	public List<Assento> getAssentos() {
		if(assentos == null){
			assentos = new ArrayList<Assento>();
		}
		preencherAssentosDisponiveis();
		return assentos;
	}

	public void preencherAssentosDisponiveis() {
		if(idVooSelecionado != null){
			vooSelecionado = new VooBO().pesquisar(idVooSelecionado);
			assentos = vooSelecionado.getAviao().getAssentos();
			/**
			 * Após preenchermos a lista de assentos com os assentos
			 * do Avião correspondente ao Vôo selecionado, verificamos
			 * quais desses estão livres, ou seja, sem reservas.
			 */
			synchronized (assentos) {
				for(Assento a: assentos){
					if(a.getReserva() != null){
						assentos.remove(a);					
					}
				}
			}
		}
	}

	public List<String> getIdAssentosEscolhidos() {
		return idAssentosEscolhidos;
	}

	public void setIdAssentosEscolhidos(List<String> idAssentosEscolhidos) {
		this.idAssentosEscolhidos = idAssentosEscolhidos;
	}

	public void setAssentos(List<Assento> assentos) {
		this.assentos = assentos;
	}

	public List<Assento> getAssentosEscolhidos() {
		if(assentosEscolhidos == null){
			assentosEscolhidos = new ArrayList<Assento>();
		}
		return assentosEscolhidos;
	}

	public void setAssentosEscolhidos(List<Assento> assentosEscolhidos) {
		this.assentosEscolhidos = assentosEscolhidos;
	}

	public Voo getVooSelecionado() {	
		if(vooSelecionado == null){
			displayErrorMessageToUser("Selecione um Vôo");
		}
		return vooSelecionado;
	}

	public Long getIdVooSelecionado() {
		return idVooSelecionado;
	}

	public void setIdVooSelecionado(Long idVooSelecionado) {
		this.idVooSelecionado = idVooSelecionado;
	}

	public void setVooSelecionado(Voo vooSelecionado) {
		this.vooSelecionado = vooSelecionado;
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
	
	
	public List<Reserva> getReservas() {
		if(reservas == null){
			reservas = new ArrayList<Reserva>();
		}
		loadReservas();
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Cliente getCliente() {
		if(cliente == null){
			setCliente((Cliente) LoginMB.getUsuarioLogado());
		}
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Integer getQtdAdultos() {
		return qtdAdultos;
	}

	public void setQtdAdultos(Integer qtdAdultos) {
		this.qtdAdultos = qtdAdultos;
	}

	public Integer getQtdCriancas() {
		return qtdCriancas;
	}

	public void setQtdCriancas(Integer qtdCriancas) {
		this.qtdCriancas = qtdCriancas;
	}

	public Integer getQtdBebesColo() {
		return qtdBebesColo;
	}

	public void setQtdBebesColo(Integer qtdBebesColo) {
		this.qtdBebesColo = qtdBebesColo;
	}

	public Integer getQtdTotal() {
		return qtdTotal;
	}

	public void setQtdTotal(Integer qtdTotal) {
		this.qtdTotal = qtdTotal;
	}

	public Integer getClasse() {
		return classe;
	}

	public void setClasse(Integer classe) {
		this.classe = classe;
	}
	
	public void setValorTotal(){
		if(qtdAdultos > 0){
			qtdTotal = qtdAdultos + qtdCriancas + qtdBebesColo;
		}
		reserva.setVoo(getVooSelecionado());
		float preco = 0;
		switch (classe) {
		case 1: //Caso seja classe Economica
			reserva.setClasse(TipoAssento.CLASSE_ECONOMICA);
			preco = qtdTotal * getVooSelecionado().getPrecoClasseEconomica();
			reserva.setPreco(preco);
			break;
		case 2:
			reserva.setClasse(TipoAssento.CLASSE_EXECUTIVA);
			preco = qtdTotal * getVooSelecionado().getPrecoClasseExecutiva();
			reserva.setPreco(preco);
			break;
		default:
			break;
		}
		
		preencherAssentosDisponiveis();
	}

	public void onRowToggle(ToggleEvent event) {  
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,  
                                            "Row State " + event.getVisibility(),  
                                            "Model:" + ((Reserva) event.getSource()).getId());  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	 public List<Voo> completeVoo(String query) {  
	        List<Voo> suggestions = new ArrayList<Voo>();  
	          
	        for(Voo p : getVoos()) {  
	            if(p.toString().contains(query))  
	                suggestions.add(p);  
	        }  
	          
	        return suggestions;  
	    }
	 
	 public ReservaBO getReservaBO() {
			if (reservaBO == null) {
				reservaBO = new ReservaBO();
			}

			return reservaBO;
		}
}