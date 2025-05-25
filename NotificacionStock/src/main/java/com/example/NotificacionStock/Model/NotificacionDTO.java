package com.example.NotificacionStock.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionDTO {

    private Long productoId;
    private String nombreProducto;
    private String mensaje;
}

