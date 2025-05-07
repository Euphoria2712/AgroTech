package com.example.DisponibilidadAsignacion.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Equipo {
    @Id
    private String id;
    private String nombre;
    private String tipo;
    private String modelo;
    private boolean activo;
    
}
