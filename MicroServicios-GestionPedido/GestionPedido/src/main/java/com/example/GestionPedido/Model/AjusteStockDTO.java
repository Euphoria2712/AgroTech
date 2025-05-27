package com.example.GestionPedido.Model;

import lombok.Data;

@Data
public class AjusteStockDTO {

    private Long id;
    private Long productoId;
    private int cantidadSolicitada;

}
