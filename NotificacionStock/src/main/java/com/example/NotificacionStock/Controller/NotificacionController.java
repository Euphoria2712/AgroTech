package com.example.NotificacionStock.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NotificacionStock.Model.NotificacionDTO;
import com.example.NotificacionStock.Model.NotificacionInventario;
import com.example.NotificacionStock.Model.NotificacionRequest;
import com.example.NotificacionStock.Service.NotificacionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/notificacion")
public class NotificacionController {

    @Autowired
    private NotificacionService notifiacionService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearNotificacion(@RequestBody NotificacionRequest request){
        try {
            NotificacionInventario notificacion = notifiacionService.crearNotificacion(request.getProductoId(), request.getMensaje(), request.getTipo());
            return ResponseEntity.ok("Notificacion creada "+ notificacion);
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear la notificacion: " + e.getMessage());
        }
    }
    
    @PostMapping
    public ResponseEntity<String> recibirNotificacion(@RequestBody NotificacionDTO notificacion) {
        // Lógica para guardar o imprimir la notificación
        System.out.println("Notificación recibida: " + notificacion.getMensaje());
        return ResponseEntity.ok("Notificación recibida");
    }


}
