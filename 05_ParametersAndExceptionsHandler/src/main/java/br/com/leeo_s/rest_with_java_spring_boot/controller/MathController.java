package br.com.leeo_s.rest_with_java_spring_boot.controller;

import br.com.leeo_s.rest_with_java_spring_boot.exception.UnsupportedMathOperationException;
import br.com.leeo_s.rest_with_java_spring_boot.request.converters.NumberConverter;
import br.com.leeo_s.rest_with_java_spring_boot.service.MathService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/math")
public class MathController {

    MathService mathService;
    NumberConverter numberConverter;

    public MathController(MathService mathService, NumberConverter numberConverter) {
        this.mathService = mathService;
        this.numberConverter = numberConverter;
    }

    @GetMapping("/sum/{number1}/{number2}")
    public Double sum(@PathVariable String number1, @PathVariable String number2) throws UnsupportedMathOperationException {

        //verificação se é numérico
        if(!numberConverter.isNumeric(number1) || !numberConverter.isNumeric(number2)) throw new UnsupportedMathOperationException("Please set a numeric value");

        return mathService.sum(numberConverter.convertToDouble(number1), numberConverter.convertToDouble(number2));
    }

    @GetMapping("/sub/{number1}/{number2}")
    public Double sub(@PathVariable String number1, @PathVariable String number2) throws UnsupportedMathOperationException {
        if(!numberConverter.isNumeric(number1) || !numberConverter.isNumeric(number2)) throw new UnsupportedMathOperationException("Please set a numeric value");

        return mathService.sub(numberConverter.convertToDouble(number1), numberConverter.convertToDouble(number2));
    }

    @GetMapping("/mult/{number1}/{number2}")
    public Double mult(@PathVariable String number1, @PathVariable String number2) throws UnsupportedMathOperationException {
        if(!numberConverter.isNumeric(number1) || !numberConverter.isNumeric(number2)) throw new UnsupportedMathOperationException("Please set a numeric value");

        return mathService.mult(numberConverter.convertToDouble(number1), numberConverter.convertToDouble(number2));
    }

    @GetMapping("/div/{number1}/{number2}")
    public Double div(@PathVariable String number1, @PathVariable String number2) throws UnsupportedMathOperationException {
        if(!numberConverter.isNumeric(number1) || !numberConverter.isNumeric(number2)) throw new UnsupportedMathOperationException("Please set a numeric value");

        return mathService.div(numberConverter.convertToDouble(number1), numberConverter.convertToDouble(number2));
    }

    @GetMapping("/med/{number1}/{number2}")
    public Double med(@PathVariable String number1, @PathVariable String number2) throws UnsupportedMathOperationException {
        if(!numberConverter.isNumeric(number1) || !numberConverter.isNumeric(number2)) throw new UnsupportedMathOperationException("Please set a numeric value");

        return mathService.med(numberConverter.convertToDouble(number1), numberConverter.convertToDouble(number2));
    }

    @GetMapping("/raiz/{number}")
    public Double raiz(@PathVariable String number) throws UnsupportedMathOperationException {
        if(!numberConverter.isNumeric(number)) throw new UnsupportedMathOperationException("Please set a numeric value");

        return mathService.raiz(numberConverter.convertToDouble(number));
    }


}
