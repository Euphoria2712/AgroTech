package com.example.ControlStock.Model;

import lombok.Data;

@Data
public class AjusteStockDTO {

    private Long productoId;
    private Long id;
    private int cantidadSolicitada;

}
