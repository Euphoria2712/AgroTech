package com.MicroEntregas.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("controllerExeptionHandler")
// Esta clase maneja las excepciones globales de la aplicacion
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        // Manejo de excepciones global
        // el error y que hace el error
        return "Ocurrió un error: " + e.getMessage();
    }

}
