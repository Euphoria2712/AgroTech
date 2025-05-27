package com.example.ControlStock.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.ControlStock.Model.UsuarioDTO;
import reactor.core.publisher.Mono;

@Service
public class ClientClient {

    private final WebClient webClient;

    public ClientClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
        .baseUrl("http://localhost:8083/api/v1/usuarios")
        .build();
    }

    public Mono<UsuarioDTO> obtenerUsuarioPorId(Long id){
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(UsuarioDTO.class)
                .onErrorResume(e -> {
                    System.out.println("Error al obtener el usuario: " + e.getMessage());
                    return Mono.empty();
                });
    }
}
