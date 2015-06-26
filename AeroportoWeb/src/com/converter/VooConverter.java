package com.converter;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.bo.VooBO;
import com.model.Voo;

public class VooConverter implements Converter {

	public List<Voo> voosBD;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		VooBO vooBO = new VooBO();
		Long vooId;

		try {
			vooId = Long.parseLong(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Dog and select it (or use the dropdow)", "Type the name of a Dog and select it (or use the dropdow)"));
		}

		return vooBO.pesquisar(vooId);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Voo voo = (Voo) arg2;
		return String.valueOf(voo.getId());
	}
	
	public List<Voo> getVoosBD() {
		if(this.voosBD == null){
			this.voosBD = new VooBO().listar();
		}
		return voosBD;
	}

	public void setVoosBD(List<Voo> voosBD) {
		this.voosBD = voosBD;
	}
}
