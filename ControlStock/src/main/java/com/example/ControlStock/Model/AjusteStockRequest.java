package com.example.ControlStock.Model;

import lombok.Data;

@Data
public class AjusteStockRequest {

    private Long productoId;
    private int cantidad;

}
