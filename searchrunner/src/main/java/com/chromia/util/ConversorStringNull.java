package com.chromia.util;

/**
 * @author Ariel Duarte
 * @since 20-Abril-2014
 * @version 1.0.0
 */

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


/**
 * 
 * Esto se implementa en la vista como un atributo para los campos de textos asi: converter="stringNull"
 * y se publica en el faces-config con el tag <converter>
 *
 */
public class ConversorStringNull implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String texto) {
		if(texto==null || texto.equals(""))
			return null;
		return texto;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object texto) {
		return texto!=null ? texto.toString() : null;
	}

}
