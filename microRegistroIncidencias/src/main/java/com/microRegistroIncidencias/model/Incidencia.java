package com.microRegistroIncidencias.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String prioridad;  //baja, media , alta 
    private String estado;  //  solucionado, en proceso y no solucionado
    private String fechaCreacion;

    @PrePersist
    public void setFechaCreacion(){
        this.fechaCreacion = LocalDateTime.now().toString();
        this.estado = "solucionado";
    }
}
