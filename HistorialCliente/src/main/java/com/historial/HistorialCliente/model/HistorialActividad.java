package com.historial.HistorialCliente.model;

import java.time.LocalDateTime;



import jakarta.persistence.Column;
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
    @Column(nullable = false)
    private Long idCliente;
    //ayudame a codificar el @NotNull para el idCliente
    @Column(nullable = false)
    private Long idActividad;
    @Column(nullable = false)
    private String tipoActividad;  // "Pedido creado", "Devolución", "Soporte"
    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private LocalDateTime fechaHora;
    @PrePersist // método @PrePersist la fecha cuando se crea
    @Column(nullable = false)
    private void AsignarFechaHora() {
        this.fechaHora = LocalDateTime.now();
    }
}
