package com.example.Productos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.Productos.DTO.StockRequest;
import com.example.Productos.Model.Producto;
import com.example.Productos.Repository.ProductoRepository;

import java.util.List;


@Service
public class ServicePL implements ServiceP {

    @Autowired 
    private ProductoRepository productoRepository;

    @Autowired
    private WebClient webClient;

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

    public Producto modificarProducto(Long id, Producto producto){
        Producto productoExistente = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        productoExistente.setNombre(producto.getNombre());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setCantidad(producto.getCantidad());
        return productoRepository.save(productoExistente);
    }
     
    @Override
    public void eliminarProductoPorId(Long id) {
        productoRepository.deleteById(id);
    }

        public Producto crearProducto(Producto producto) {
        Producto nuevo = productoRepository.save(producto);

        notificarNuevoStock(nuevo.getId(), nuevo.getCantidad(), 5);

        return nuevo;
    }

    private void notificarNuevoStock(Long productoId, int cantidad, int minimo) {
        StockRequest request = new StockRequest(productoId, cantidad, minimo);

        webClient.post()
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(resp -> System.out.println("Respuesta desde Stock: " + resp));
    }

}
