package com.microRegistroIncidencias.service;

import java.util.List;

import com.microRegistroIncidencias.model.Incidencia;

public interface IncidenciasService {

    Incidencia crearIncidencia(Incidencia incidencia);
    List<Incidencia> listarTodas();
    Incidencia obtenerPorId(long id);
    void eliminar(long id);
}