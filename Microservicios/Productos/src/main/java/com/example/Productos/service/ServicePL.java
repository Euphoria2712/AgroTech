package com.example.Productos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Productos.Model.Producto;
import com.example.Productos.Repository.ProductoRepository;

import java.util.List;

@Service
public class ServicePL implements ServiceP {

    @Autowired 
    private ProductoRepository productoRepository;

    @Override 
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto buscarProductoPorId(Long id){
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    }

    @Override
    public List<Producto> buscarTodosLosProductos() {
        return productoRepository.findAll();

    }

    @Override 
    @RequestMapping(value = "/productos/{id}", method = RequestMethod.PUT)
    public Producto modificarProducto(@PathVariable Long id, @RequestBody Producto producto){
        Producto productoExistente = productoRepository.findById(id).orElseThrow()(() -> new RuntimeException("Producto no encontrado"));

        productoExistente.setNombre(producto.getNombre());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setCantidad(producto.getCantidad());
        return productoRepository.save(productoExistente);
    }
     


    public void eliminarProductoPorId(Long id) {
        productoRepository.deleteById(id);
    }
}
