package com.example.DisponibilidadAsignacion.Controller;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

class ReservaRequest {
    private String equipoId;
    private String pedidoId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
