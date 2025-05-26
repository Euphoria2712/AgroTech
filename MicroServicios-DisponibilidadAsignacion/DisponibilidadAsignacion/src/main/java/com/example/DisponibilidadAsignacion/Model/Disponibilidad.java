package com.example.DisponibilidadAsignacion.Model;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientSsl;

import com.example.DisponibilidadAsignacion.Repository.DisponibilidadRepository;
import com.example.DisponibilidadAsignacion.Repository.EquipoRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "disponibilidades") // Nombre Bd
@Entity

public class Disponibilidad {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "equipo_id", nullable = false)
    private String equipoId;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
