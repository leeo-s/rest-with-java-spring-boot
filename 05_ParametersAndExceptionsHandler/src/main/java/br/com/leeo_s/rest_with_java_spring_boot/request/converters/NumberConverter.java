package br.com.leeo_s.rest_with_java_spring_boot.request.converters;

import br.com.leeo_s.rest_with_java_spring_boot.exception.UnsupportedMathOperationException;
import org.springframework.stereotype.Component;

@Component
public class NumberConverter {
    public static Double convertToDouble(String strNumber) throws UnsupportedMathOperationException {
        if(strNumber == null || strNumber.isEmpty()) throw new UnsupportedMathOperationException("Please set a numeric value");

        //tratamento da string numérica
        String number = strNumber.replace(",",".");

        return Double.parseDouble(strNumber);
    }

    public static boolean isNumeric(String strNumber) {
        if(strNumber == null || strNumber.isEmpty()) return false;

        //tratamento da string numérica
        String number = strNumber.replace(",",".");

        //regex para verificar se é numerio, positivo ou negativo, que vai de 0 a 9, que tem um separador ponto
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
