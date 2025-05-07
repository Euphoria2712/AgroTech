package com.example.DisponibilidadAsignacion.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DisponibilidadAsignacion.Model.Disponibilidad;
import com.example.DisponibilidadAsignacion.Model.Equipo;
import com.example.DisponibilidadAsignacion.Model.EstadoDisponibilidad;
import com.example.DisponibilidadAsignacion.Repository.DisponibilidadRepository;
import com.example.DisponibilidadAsignacion.Repository.EquipoRepository;

@Service

public class DisponibilidadService {
    
    @Autowired
    private DisponibilidadRepository disponibilidadRepo;

    @Autowired 
    private EquipoRepository equipoRepo;

    public List<Equipo> consultarDisponibilidad(LocalDate fechaInicio, LocalDate fechaFin, String tipoEquipo){
        
// Obtener equipos del tipo solicitando
        List<Equipo> equipos = tipoEquipo != null ? 
            equipoRepo.findByTipo(tipoEquipo):
            equipoRepo.findByActivoTtrue();

// Filtrar equipos disponibles
        return equipos.stream()
        .filter(equipo -> {
            List<Disponibilidad> disponibilidades = disponibilidadRepo
            .findDisponibilidadPorRangoFechas(
                equipo.getId(),
                fechaInicio,
                fechaFin);
            return !disponibilidades.isEmpty();
        })
        .collect(Collectors.toList());
    }

    public boolean reservarEquipo(String equipoId, String pedidoId, LocalDate fechaInicio, LocalDate fechaFin){
// Verificar disponibilidad actual
        
        List<Disponibilidad> disponibilidades = disponibilidadRepo
        .findDisponibilidadPorRangoFechas(equipoId, fechaInicio, fechaFin);

        if(disponibilidades.isEmpty()){
            return false;
        }
        Disponibilidad reserva = new Disponibilidad();
        reserva.setEquipoId(equipoId);
        reserva.setPedidoId(pedidoId);
        reserva.setFechaInicio(fechaInicio);
        reserva.setFechaFin(fechaFin);
        reserva.setEstado(EstadoDisponibilidad.RESERVADO);
    
    disponibilidadRepo.save(reserva);
    return true;
    }

    public void liberarEquipos(String pedidoId){
        List<Disponibilidad> asignaciones = disponibilidadRepo.findByPedidoId(pedidoId);

        asignaciones.forEach(asignacion -> {
            asignacion.setPedidoId(null);
            asignacion.setEstado(EstadoDisponibilidad.DISPONIBLE);
            disponibilidadRepo.save(asignacion);
        });

    }
}
