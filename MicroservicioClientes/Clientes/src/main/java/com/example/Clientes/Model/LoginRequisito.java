package com.example.Clientes.Model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class LoginRequisito {
    
    @Column(unique = true)
    private String correo;
    @Column(unique = true)
    private String contraseña;

}
