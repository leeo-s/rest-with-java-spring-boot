package br.com.leeo_s.rest_with_java_spring_boot.service;

import br.com.leeo_s.rest_with_java_spring_boot.exception.UnsupportedMathOperationException;
import org.springframework.stereotype.Service;

@Service
public class MathService {

    public Double sum(Double number1, Double number2) {
        return number1 + number2;
    }

    public Double sub(Double number1, Double number2) {
        return number1 - number2;
    }

    public Double mult(Double number1, Double number2) {
        return number1 * number2;
    }

    public Double div(Double number1, Double number2) {
        return number1 / number2;
    }

    public Double med(Double number1, Double number2) {
        return (number1 + number2) / 2;
    }

    public Double raiz(Double number) {
        return Math.sqrt(number);
    }
}
