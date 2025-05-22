package com.microRegistroIncidencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microRegistroIncidencias.model.Incidencia;

// esto le deice al spring como trabajar en la base de datos, hereda metodos, ssave, findAll,deleteById etc
public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {

}
