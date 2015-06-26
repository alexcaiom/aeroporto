package com.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bo.VooBO;
import com.model.Voo;
import com.sun.faces.context.flash.ELFlash;
import com.util.Constantes;

@ViewScoped
@ManagedBean
public class VooMB extends AbstractMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Voo voo;
	private List<Voo> voos;
	private VooBO vooBO;
	private String SELECTED_VOO = "selectedVoo";

	public VooBO getVooBO() {
		if (vooBO == null) {
			vooBO = new VooBO();
		}

		return vooBO;
	}

	public void create() {
		try {
			getVooBO().createVoo(voo);
			closeDialog();
			displayInfoMessageToUser(Constantes.getMensagemSucesso(Voo.class, Constantes.OPERACAO_INCLUSAO));
			loadVoos();
			reset();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser(Constantes.getMensagemFalha(Voo.class, Constantes.OPERACAO_INCLUSAO));
			e.printStackTrace();
		}
	}
	
	public void update() {
		try {
			getVooBO().updateVoo(voo);
			closeDialog();
			displayInfoMessageToUser(Constantes.getMensagemSucesso(Voo.class, Constantes.OPERACAO_ATUALIZACAO));
			loadVoos();
			reset();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser(Constantes.getMensagemFalha(Voo.class, Constantes.OPERACAO_ATUALIZACAO));
			e.printStackTrace();
		}
	}
	
	public void delete() {
		try {
			getVooBO().deleteVoo(voo);
			closeDialog();
			displayInfoMessageToUser(Constantes.getMensagemSucesso(Voo.class, Constantes.OPERACAO_EXCLUSAO));
			loadVoos();
			reset();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser(Constantes.getMensagemFalha(Voo.class, Constantes.OPERACAO_EXCLUSAO));
			e.printStackTrace();
		}
	}
	public void detalharVoo(){
		if (voo == null) {
			voo = (Voo) ELFlash.getFlash().get(SELECTED_VOO);
			voo = getVooBO().pesquisar(voo.getId());
		}
	}

	public List<Voo> getVoos() {
		if (voos == null) {
			loadVoos();
			if(voos != null && !voos.isEmpty()){
				voo = voos.get(0);
			}
		}

		return voos;
	}

	private void loadVoos() {
		voos = getVooBO().listar();
	}

	public Voo getVoo() {
		if(voo == null){
			detalharVoo();
		}
		return voo;
	}

	public void setVoo(Voo voo) {
		this.voo = voo;
	}

	public void reset() {
		voo = new Voo();
	}
}