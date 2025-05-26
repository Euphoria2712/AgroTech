package com.example.ControlStock.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.ControlStock.Model.NotificacionDTO;

import reactor.core.publisher.Mono;

@Service
public class NotificationClient {

   @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<String> enviarNotificacion(NotificacionDTO dto) {
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8086/api/v1/notificacion") // Puerto del micro de notificaciones
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(String.class);
    }

}
