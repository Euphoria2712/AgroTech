package com.historial.HistorialCliente.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.historial.HistorialCliente.model.HistorialActividad;


import com.historial.HistorialCliente.repository.HistorialActividadRepository;
@Service
public class HistorialServicioLogico implements HistorialServicio{

    @Autowired
    private HistorialActividadRepository repository;

    @Override
    public HistorialActividad registrar(HistorialActividad historial){
        return repository.save(historial);

    }
    @Override
    public List<HistorialActividad> ObtenerTodosLosHistoriales() {
        return repository.findAll();
    }
    @Override
    public HistorialActividad ObtenerPorId(long id) {
        return repository.findById(id).orElse(null);
    }
    @Override
    public List<HistorialActividad> BuscarPorIdCliente(long idCliente) {
        return repository.findByIdCliente(idCliente);
    }
    @Override
    public List<HistorialActividad> BuscarPorTipoActividad(String tipoActividad) {
        return repository.findByTipoActividad(tipoActividad);
        //findByDescripcionContaining busca todas las actividades que contengan la palabra clave en su descripcion
    }
    @Override
    public List<HistorialActividad> buscarPorDescripcionActividad(String keyword) {
        return repository.findByDescripcionContaining(keyword);

    }
    @Override
    public List<HistorialActividad> BuscarPorFechaEntre(LocalDateTime desde, LocalDateTime hasta) {
        return repository.findByFechaHoraBetween(desde, hasta);
    }
}