package com.model.usuario;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="estadoCivilMB")
public enum EstadoCivil {
	SOLTEIRO,
	CASADO,
	VIUVO,
	DIVORCIADO;
}
