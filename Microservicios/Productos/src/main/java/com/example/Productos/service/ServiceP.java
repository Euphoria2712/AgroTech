package com.example.Productos.service;
import java.util.List;

import com.example.Productos.Model.Producto;

public interface ServiceP {

    Producto guardarProducto(Producto producto);
    Producto buscarProductoPorId(Long id);
    List<Producto> buscarTodosLosProductos();
    void eliminarProductoPorId(Long id);
}
