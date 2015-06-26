package com.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.bo.ClienteBO;
import com.model.Voo;

@FacesConverter(forClass = com.model.usuario.Cliente.class)
public class ClienteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		ClienteBO clienteBO = new ClienteBO();
		Long clienteId;

		try {
			clienteId = Long.parseLong(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Dog and select it (or use the dropdow)", "Type the name of a Dog and select it (or use the dropdow)"));
		}

		return clienteBO.pesquisar(clienteId);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Voo dog = (Voo) arg2;
		return String.valueOf(dog.getId());
	}
}
