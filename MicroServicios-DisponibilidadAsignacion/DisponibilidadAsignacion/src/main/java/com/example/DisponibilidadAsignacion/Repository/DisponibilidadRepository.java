package com.example.DisponibilidadAsignacion.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.DisponibilidadAsignacion.Model.Disponibilidad;

public interface DisponibilidadRepository extends JpaRepository<Disponibilidad, Long>   {
    @Query("SELECT d FROM Disponibilidad d WHERE " +
    "d.equipoId = :equipoId AND " +
    "d.estado = 'DISPONIBLE' AND " +
    "((d.fechaInicio <= :fechaFin AND d.fechaFin >= :fechaInicio) OR " +
    "(d.fechaInicio IS NULL AND d.fechaFin IS NULL))")
    
     List<Disponibilidad> findDisponibilidadPorRangoFechas(
            @Param("equipoId") String equipoId,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);
    
    List<Disponibilidad> findByPedidoId(String pedidoId);
}