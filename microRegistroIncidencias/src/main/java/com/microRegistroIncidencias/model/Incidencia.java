package com.microRegistroIncidencias.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity

public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
