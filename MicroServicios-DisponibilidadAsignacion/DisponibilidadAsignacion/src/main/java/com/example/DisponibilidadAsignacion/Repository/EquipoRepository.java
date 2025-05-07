package com.example.DisponibilidadAsignacion.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DisponibilidadAsignacion.Model.Equipo;

public interface EquipoRepository extends JpaRepository<Equipo, String> {
    List <Equipo> findByTipo(String tipo);
    List <Equipo> findByActivoTtrue();
}
