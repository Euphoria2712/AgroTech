package com.MicroEntregas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.MicroEntregas.model.Entrega;

import jakarta.transaction.Transactional;


public interface EntregaRepository extends JpaRepository<Entrega, Long> {
    
   //JPQL: Buscar entregas por estado
   @Query("SELECT e FROM Entrega e WHERE e.estado = :estado")
   List<Entrega> BuscarEntregasPorEstado(@Param("estado")String estado);

   // SQL: Buscar entregas por transpotista
   @Query(value = "SELECT * FROM entregas WHERE id_transportista = :idtranportista", nativeQuery = true)
   List<Entrega> buscarEntregasPorTransportistas(@Param("idtranportista") long id_transportista);
   // JPQL: Buscar entregas por rango de fechas
   @Query("SELECT e FROM Entrega e WHERE e.fechaEntrega BETWEEN :fechaInicio AND :fechaFin")
   List<Entrega> buscarEntregasPorRangoFechas(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);

   //resetear el estado de las entregas
   @Query("UPDATE Entrega e SET e.estado = 'Pendiente' WHERE e.estado = 'Entregada'")
   void resetearEstadoEntregas(); // Cambia el estado de las entregas a 'Pendiente' si están en 'Entregada'

   //resetear auto increment
   @Transactional
   @Modifying
   @Query(value = "ALTER TABLE entregas AUTO_INCREMENT = 1", nativeQuery = true)
   void resetAutoIncrement();
}
