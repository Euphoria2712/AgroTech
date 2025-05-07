package com.example.DisponibilidadAsignacion.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Disponibilidad {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String equipoId;
    private String fechaInicio;
    private LocalDate fechaFin;
    private String pedidoId;
    private EstadoDisponibilidad estado;

    public void setFechaInicio(LocalDate fechaInicio) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
