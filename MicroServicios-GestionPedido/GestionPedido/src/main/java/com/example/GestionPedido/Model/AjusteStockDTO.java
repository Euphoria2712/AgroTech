package com.example.GestionPedido.Model;

import lombok.Data;

@Data
public class AjusteStockDTO {
    private Long id; // ID del cliente
    private Long productoId;
    private int cantidadSolicitada;;

    // Getters y setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductoId() {
        return productoId;
    }
    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidadSolicitada;
    }
    public void setCantidad(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }
}