package com.example.GestionPedido.Model;
import lombok.Data;
@Data

public class UsuariosDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private String telefono;
    private String direccion;
    private String rol;
}
