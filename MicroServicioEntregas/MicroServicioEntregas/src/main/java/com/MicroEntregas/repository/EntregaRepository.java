package com.MicroEntregas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.MicroEntregas.model.Entrega;


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


}
