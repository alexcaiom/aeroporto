/**
 * 
 */
package com.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Alex
 *
 */
public class FormatadorHora {
	
	public static final SimpleDateFormat formatador = new SimpleDateFormat(Constantes.FORMATO_DATA_HORA);

	public static String formataDDMMAAAAHHMMSS(Calendar data){
		return formatador.format(data.getTime());
	}
	
}
