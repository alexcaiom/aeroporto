package com.util;

import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.Years;

public class UtilsData {

	public static Integer getIdadePelaDataDeNascimento(Date dataNascimento) {
		LocalDate nascimento = new LocalDate(dataNascimento.getTime());
		LocalDate hoje = new LocalDate();
		Years idade = Years.yearsBetween(nascimento, hoje);
		return idade.getYears();
	}

}
