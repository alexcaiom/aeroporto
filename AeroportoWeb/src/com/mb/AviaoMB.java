package com.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bo.AssentoBO;
import com.bo.AviaoBO;
import com.model.Assento;
import com.model.Aviao;
import com.model.Operadora;
import com.model.TipoAssento;
import com.model.Voo;
import com.sun.faces.context.flash.ELFlash;
import com.util.Constantes;

@ViewScoped
@ManagedBean
public class AviaoMB extends AbstractMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Aviao aviao;
	private List<Aviao> avioes;
	private List<Assento> assentos;
	private String SELECTED_VOO = "selectedAviao";
	private AviaoBO aviaoBO;
	private List<Operadora> operadoras;
	
	private Integer qtdAssentosEconomica;
	private Integer qtdAssentosExecutiva;

	public AviaoBO getAviaoBO() {
		if (aviaoBO == null) {
			aviaoBO = new AviaoBO();
		}

		return aviaoBO;
	}

	public void create() {
		try {
			assentos = new ArrayList<Assento>();
			for(int cont = 0; cont < qtdAssentosEconomica ; cont++) {
				Assento assento = new Assento();
				assento.setNome("economica"+(cont+1));
				assento.setTipo(TipoAssento.CLASSE_ECONOMICA);
				assentos.add(assento);
			}
			for(int cont = 0; cont < qtdAssentosExecutiva; cont++) {
				Assento assento = new Assento();
				assento.setNome("executiva"+(cont+1));
				assento.setTipo(TipoAssento.CLASSE_EXECUTIVA);
				assentos.add(assento);
			}
			aviao.setAssentos(assentos);
			getAviaoBO().inserir(aviao);
			closeDialog();
			displayInfoMessageToUser(Constantes.getMensagemSucesso(Voo.class, Constantes.OPERACAO_INCLUSAO));
			preencheAvioes();
			reset();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser(Constantes.getMensagemFalha(Voo.class, Constantes.OPERACAO_INCLUSAO));
			e.printStackTrace();
		}
	}
	
	public void update() {
		try {
			getAviaoBO().atualizar(aviao);
			closeDialog();
			displayInfoMessageToUser(Constantes.getMensagemSucesso(Voo.class, Constantes.OPERACAO_ATUALIZACAO));
			preencheAvioes();
			reset();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser(Constantes.getMensagemFalha(Voo.class, Constantes.OPERACAO_ATUALIZACAO));
			e.printStackTrace();
		}
	}
	
	public void delete() {
		try {
			getAviaoBO().excluir(aviao);
			closeDialog();
			displayInfoMessageToUser(Constantes.getMensagemSucesso(Voo.class, Constantes.OPERACAO_EXCLUSAO));
			preencheAvioes();
			reset();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser(Constantes.getMensagemFalha(Voo.class, Constantes.OPERACAO_EXCLUSAO));
			e.printStackTrace();
		}
	}
	public void detalharAviao(){
		if (aviao == null) {
			aviao = (Aviao) ELFlash.getFlash().get(SELECTED_VOO);
			aviao = getAviaoBO().pesquisar(aviao.getId());
		}
	}


	private void preencheAvioes() {
		avioes = getAviaoBO().listar();
	}
	private void preencheAssentos() {
		assentos = new AssentoBO().listar();
	}

	public Aviao getAviao() {
		if(aviao == null){
			if(avioes != null && !avioes.isEmpty()) {
				aviao = avioes.get(0);
			} else {
				aviao = new Aviao();
			}
		}
		if(aviao != null && aviao.getId() != null){
			detalharAviao();
		}
		return aviao;
	}


	public void setAviao(Aviao aviao) {
		this.aviao = aviao;
	}

	public List<Aviao> getAvioes() {
		if(this.avioes == null) {
			this.avioes = new ArrayList<Aviao>();
		}
		carregarAvioes();
		return avioes;
	}

	private void carregarAvioes() {
		avioes = new AviaoBO().listar();
	}

	public void setAvioes(List<Aviao> avioes) {
		this.avioes = avioes;
	}

	public List<Assento> getAssentos() {
		if(assentos == null){
			assentos = new ArrayList<Assento>();
		}
		preencheAssentos();
		return assentos;
	}

	public void setAssentos(List<Assento> assentos) {
		this.assentos = assentos;
	}

	public void setAviaoBO(AviaoBO aviaoBO) {
		this.aviaoBO = aviaoBO;
	}

	public List<Operadora> getOperadoras() {
		return operadoras;
	}

	public void setOperadoras(List<Operadora> operadoras) {
		this.operadoras = operadoras;
	}

	public Integer getQtdAssentosEconomica() {
		if (this.qtdAssentosEconomica == null) {
			if (getAviao() != null) {
				List<Assento> assentos = getAviao().getAssentos();
				this.qtdAssentosEconomica = 0;
				for (Assento assento : assentos) {
					if (assento.getTipo() == TipoAssento.CLASSE_ECONOMICA) {
						this.qtdAssentosEconomica++;
					}
				}
			}
		}
		return qtdAssentosEconomica;
	}

	public void setQtdAssentosEconomica(Integer qtdAssentosEconomica) {
		this.qtdAssentosEconomica = qtdAssentosEconomica;
	}

	public Integer getQtdAssentosExecutiva() {
		if (this.qtdAssentosExecutiva == null) {
			if (getAviao() != null) {
				List<Assento> assentos = getAviao().getAssentos();
				this.qtdAssentosExecutiva = 0;
				for (Assento assento : assentos) {
					if (assento.getTipo() == TipoAssento.CLASSE_EXECUTIVA) {
						this.qtdAssentosExecutiva++;
					}
				}
			}
		}
		return qtdAssentosExecutiva;
	}

	public void setQtdAssentosExecutiva(Integer qtdAssentosExecutiva) {
		this.qtdAssentosExecutiva = qtdAssentosExecutiva;
	}

	public void reset() {
		aviao = new Aviao();
	}
}