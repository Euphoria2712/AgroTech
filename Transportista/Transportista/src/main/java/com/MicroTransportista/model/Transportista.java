package com.MicroTransportista.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "transportistas") //nombre bD
@AllArgsConstructor
@NoArgsConstructor
public class Transportista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Nombre_completo", nullable = false, length = 100)
    private String nombre;
    @Column(name = "RUT", nullable = false, unique = true, length = 12)
    private String rut;
    @Column(name = "Telefono", nullable = false, length = 15)
    private String telefono;
    @Column(name = "Estado", nullable = false, length = 20)
    private String estado;
}
