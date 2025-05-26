package com.historial.HistorialCliente.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.historial.HistorialCliente.model.HistorialActividad;
public interface HistorialActividadRepository extends JpaRepository<HistorialActividad, Long> {
    List<HistorialActividad> findByIdCliente(Long idCliente);
    List<HistorialActividad> findByTipoActividad(String tipoActividad);
    List<HistorialActividad> findByDescripcionContaining(String descripcion);
    List<HistorialActividad> findByFechaHoraBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<HistorialActividad> findByIdClienteOrderByFechaHoraDesc(Long idCliente);

}
