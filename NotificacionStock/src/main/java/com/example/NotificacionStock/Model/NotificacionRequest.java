package com.example.NotificacionStock.Model;

import lombok.Data;

@Data
public class NotificacionRequest {

    private Long productoId;
    private String mensaje;
    private String tipo;

}
