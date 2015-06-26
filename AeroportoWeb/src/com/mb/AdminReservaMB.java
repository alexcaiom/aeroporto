package com.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bo.VooBO;
import com.model.Voo;

@ViewScoped
@ManagedBean
public class AdminReservaMB extends AbstractMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Voo voo;
	private List<Voo> voos;
	private VooBO vooFacade;

	public VooBO getVooFacade() {
		if (vooFacade == null) {
			vooFacade = new VooBO();
		}

		return vooFacade;
	}

	public void createVoo() {
		try {
			getVooFacade().createVoo(voo);
			closeDialog();
			displayInfoMessageToUser("Created With Sucess");
			loadVoos();
			resetVoo();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}
	
	public void updateVoo() {
		try {
			getVooFacade().updateVoo(voo);
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
	
	public void deleteDog() {
		try {
			getVooFacade().deleteVoo(voo);
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

	public List<Voo> getAllVoos() {
		if (voos == null) {
			loadVoos();
		}

		return voos;
	}

	private void loadVoos() {
		voos = getVooFacade().listar();
	}

	public void resetVoo() {
		voo = new Voo();
	}
}