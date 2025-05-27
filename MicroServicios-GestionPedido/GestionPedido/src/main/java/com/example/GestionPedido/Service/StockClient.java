package com.example.GestionPedido.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.GestionPedido.Model.AjusteStockDTO;

import reactor.core.publisher.Mono;

@Service
public class StockClient {

    private final WebClient webClient;

    @Autowired
    public StockClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> ajustarStock(Long id, Long productoId, int cantidad) {
        AjusteStockDTO dto = new AjusteStockDTO();
        dto.setId(id);
        dto.setProductoId(productoId);
        dto.setCantidadSolicitada(cantidad);

        return webClient.post()
                .uri("/ajustar")
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> {
                    System.out.println(" Error al ajustar stock: " + e.getMessage());
                    return Mono.just("Error al ajustar stock");
                });
    }
}
