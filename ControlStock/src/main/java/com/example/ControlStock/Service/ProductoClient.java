package com.example.ControlStock.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class ProductoClient {

    private final WebClient webClient;

    @Autowired
    public ProductoClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
            .baseUrl("http://localhost:8082/api/v1/productos")
            .build();
    }

    public Mono<String> obtenerNombreProducto(Long id) {
        return webClient
            .get()
            .uri("/{id}", id)  
            .retrieve()
            .bodyToMono(String.class);
    }
}