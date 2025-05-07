package com.MicroEntregas.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "entregas")
@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * Clase que representa una entrega en el sistema.
 * Contiene información sobre el transportista, estado y observaciones de la entrega.
 */
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transportista;
    private String estado;
    private String observaciones;
}   
