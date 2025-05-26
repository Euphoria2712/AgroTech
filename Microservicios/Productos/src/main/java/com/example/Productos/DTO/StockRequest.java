package com.example.Productos.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockRequest {

    private Long productoId;
    private int cantidadActual;
    private int cantidadMinima;
}
