package com.example.Productos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Productos.service.ServicePL;
import com.example.Productos.Model.Producto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/productos")
public class ControladorProducto {

    @Autowired
    private ServicePL servicePL;

    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto){
        return servicePL.guardarProducto(producto);
    }
    
    @GetMapping("/{id}")
    public Producto buscarProductoPorId()
    



}
