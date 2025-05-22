package com.microRegistroIncidencias.exeption;

// lo que hace es crear una excepcion personalizada
// para que cuando no encuentre un recurso, se lance esta excepcion
// y no la de spring, que es mas generica
public class NotFoundException extends RuntimeException{
    public NotFoundException(String mensaje){
        super(mensaje);
    }

}
