package com.historial.HistorialCliente.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.historial.HistorialCliente.model.HistorialActividad;
import com.historial.HistorialCliente.service.HistorialServicio;

@RestController
@RequestMapping("/historial")
public class historialClienteController {

    @Autowired
    private HistorialServicio servicio;

    @PostMapping("/registrar")
    public ResponseEntity<?> crear(@Validated @RequestBody HistorialActividad actividad) {
        HistorialActividad actividadCreada = servicio.registrar(actividad);
        return ResponseEntity.ok(actividadCreada);
    }
    @PostMapping
    public HistorialActividad registrarActividad(@Validated @RequestBody HistorialActividad actividad){
        return servicio.registrar(actividad);

    }
    @GetMapping
    public List<HistorialActividad> listarTodoCliente(){
        return servicio.ObtenerTodosLosHistoriales();
        
    }
    @GetMapping("/{id}")
    public HistorialActividad listarPorId(@PathVariable long idCliente){
        return servicio.ObtenerPorId(idCliente);
    }
    @GetMapping("/cliente/{idCliente}")
    public List<HistorialActividad> listarPorIdCliente(@PathVariable long idCliente){
        return servicio.BuscarPorIdCliente(idCliente);
    }
    @GetMapping("/descripcion/{descripcion}")
    public List<HistorialActividad> listarPorDescripcion(@PathVariable String descripcion){
        return servicio.BuscarPorTipoActividad(descripcion);
    }
    @GetMapping("/fecha/{fecha}")
    public List<HistorialActividad> buscarPorFechaExacta(@PathVariable String fecha) {
        LocalDateTime desde = LocalDateTime.parse(fecha + "T00:00:00");
        LocalDateTime hasta = LocalDateTime.parse(fecha + "T23:59:59");
        return servicio.BuscarPorFechaEntre(desde, hasta);
    }

}
