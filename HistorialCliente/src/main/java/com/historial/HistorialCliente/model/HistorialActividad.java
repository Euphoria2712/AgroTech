package com.historial.HistorialCliente.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialActividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
   
    private Long idCliente;
    //ayudame a codificar el @NotNull para el idCliente

    @NotNull(message = "El id del cliente no puede estar vacío")
    private Long idActividad;
    @NotNull(message = "El tipo de actividad no puede estar vacío")
    private String tipoActividad;  // "Pedido creado", "Devolución", "Soporte"
    @NotNull(message = "La descripción no puede estar vacía")
    private String descripcion;
    private LocalDateTime fechaHora;
    @PrePersist // método @PrePersist la fecha cuando se crea
    private void AsignarFechaHora() {
        this.fechaHora = LocalDateTime.now();
    }
}
