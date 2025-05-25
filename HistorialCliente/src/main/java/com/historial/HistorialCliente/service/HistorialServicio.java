package com.historial.HistorialCliente.service;

import java.time.LocalDateTime;
import java.util.List;

import com.historial.HistorialCliente.model.HistorialActividad;

public interface HistorialServicio {
    
    HistorialActividad registrar(HistorialActividad Historial);
    List<HistorialActividad> ObtenerTodosLosHistoriales();
    HistorialActividad ObtenerPorId(long id);
    List<HistorialActividad> BuscarPorIdCliente(long idCliente);
    List<HistorialActividad> BuscarPorTipoActividad(String tipoActividad);
    List<HistorialActividad> buscarPorDescripcionActividad(String keyword);
    List<HistorialActividad> BuscarPorFechaEntre(LocalDateTime desde, LocalDateTime hasta);
}
