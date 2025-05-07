package com.example.DisponibilidadAsignacion.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DisponibilidadAsignacion.Model.Equipo;
import com.example.DisponibilidadAsignacion.Service.DisponibilidadService;

@RestController
@RequestMapping

public class DisponibilidadController {
    
    @Autowired
    private DisponibilidadService disponibilidadService;
    
    @GetMapping
    public List<Equipo> consultarDisponibilidad(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam(required = false) String tipo) {
        
        if(fechaInicio == null) fechaInicio = LocalDate.now();
        if(fechaFin == null) fechaFin = fechaInicio.plusDays(7);
        
        return disponibilidadService.consultarDisponibilidad(fechaInicio, fechaFin, tipo);
    }
    
    @PostMapping("/reservar")
    public ResponseEntity<?> reservarEquipo(@RequestBody ReservaRequest request) {
        boolean reservado = disponibilidadService.reservarEquipo(
            request.getEquipoId(),
            request.getPedidoId(),
            request.getFechaInicio(),
            request.getFechaFin());
            
        return reservado ? 
            ResponseEntity.ok().build() :
            ResponseEntity.status(HttpStatus.CONFLICT).body("Equipo no disponible");
    }
    
    @PostMapping("/liberar/{pedidoId}")
    public ResponseEntity<Void> liberarEquipos(@PathVariable String pedidoId) {
        disponibilidadService.liberarEquipos(pedidoId);
        return ResponseEntity.ok().build();
    }
}