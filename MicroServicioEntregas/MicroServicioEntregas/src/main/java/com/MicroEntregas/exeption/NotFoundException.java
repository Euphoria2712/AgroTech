package com.MicroEntregas.exeption;
//esto define una excepcion personalizada que extiende de RuntimeException
// RuntimeException es una clase de excepcion que se lanza en tiempo de ejecucion
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }

}