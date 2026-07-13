package br.com.leeo_s.rest_with_java_spring_boot.exception;

//um record foi introduzido no Java 14, ele simplifica a criação de classes que vão apenas armazenar valores
//não vai mudar durante a instancia da classe

import java.util.Date;

//além disso promove a imutabilidades e deixa o código mais claro, deixando explícito o que é essa classe
public record ExceptionResponse(Date timestamp, String message, String details) {}
