package br.com.carrinho.handler.util;

import br.com.carrinho.service.exception.NullParameterException;

/**
 * Author: Marcos Pinho
 * E-mail: marcosjava2008@gmail.com
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

    public static String firstLetterUppercase(String texto) {
        if(isEmpty(texto)) {
            throw new NullParameterException();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Character.toUpperCase(texto.charAt(0)));
        stringBuilder.append(texto.substring(1));
        return stringBuilder.toString();
    }

} 
