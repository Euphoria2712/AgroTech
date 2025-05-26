package com.example.Productos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Productos.service.ServicePL;
import com.example.Productos.Model.Producto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;


@RestController
@RequestMapping("api/v1/productos")
public class ControladorProducto {

    @Autowired
    private ServicePL servicePL;

    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto){
        return servicePL.guardarProducto(producto);
    }
    
    @GetMapping("/{id}")
    public Producto buscarProductoPorId(@PathVariable Long id){
        return servicePL.buscarProductoPorId(id);

    }

    @GetMapping
    public List<Producto> buscarTodosLosProductos(){
        return servicePL.buscarTodosLosProductos();
    }

    @PutMapping("/{id}")
    public Producto modificarProducto(@PathVariable Long id, @RequestBody Producto producto){
        return servicePL.modificarProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProductoPorId(@PathVariable Long id){
        servicePL.eliminarProductoPorId(id);
    }
    

}
