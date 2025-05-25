package com.example.NotificacionStock.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NotificacionStock.Model.NotificacionInventario;
import com.example.NotificacionStock.Repository.NotificacionRepositorio;


@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepositorio NotificacionRepositorio;

    public NotificacionInventario crearNotificacion(Long productoId, String mensaje, String tipo ) {
        NotificacionInventario notificacion = NotificacionInventario.builder()
                .productoId(productoId)
                .mensaje(mensaje)
                .tipo(tipo)
                .fecha(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
        return NotificacionRepositorio.save(notificacion);
    }
}
