package com.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.bo.ReservaBO;
import com.model.Reserva;

@FacesConverter(forClass = com.model.Reserva.class)
public class ReservaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		ReservaBO reservaBO = new ReservaBO();
		Long reservaId;

		try {
			reservaId = Long.parseLong(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Dog and select it (or use the dropdow)", "Type the name of a Dog and select it (or use the dropdow)"));
		}

		return reservaBO.pesquisar(reservaId);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Reserva reserva = (Reserva) arg2;
		return String.valueOf(reserva.getId());
	}
}
