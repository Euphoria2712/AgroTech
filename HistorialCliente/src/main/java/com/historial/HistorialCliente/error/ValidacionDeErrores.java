package com.historial.HistorialCliente.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ValidacionDeErrores {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity manejarErrores(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();

        return ResponseEntity.badRequest().body("Error al validar : "+ mensaje);
    }

}
