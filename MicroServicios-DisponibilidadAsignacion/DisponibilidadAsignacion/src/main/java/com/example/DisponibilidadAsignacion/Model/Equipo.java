package com.example.DisponibilidadAsignacion.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipos") // Nombre de la tabla en la base de datos
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Equipo {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "modelo", nullable = false, length = 50)
    private String modelo;

    @Column(name = "activo", nullable = false)
    private boolean activo;
    
}
