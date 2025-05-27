package com.example.DisponibilidadAsignacion.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientSsl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.DisponibilidadAsignacion.Model.Disponibilidad;
import com.example.DisponibilidadAsignacion.Model.Equipo;
import com.example.DisponibilidadAsignacion.Repository.DisponibilidadRepository;
import com.example.DisponibilidadAsignacion.Repository.EquipoRepository;

@Service

public class DisponibilidadService {
    private static final String WEB_CLIENT_URL = "http://localhost:8080/api/v1/equipos";

    @Autowired
    private DisponibilidadRepository disponibilidadRepo;

    @Autowired
    private EquipoRepository equipoRepo;

    @Autowired
    private WebClient webClient;

    @Bean
    public WebClient disponibilidadWebClient() {
        return WebClient.builder()
                .baseUrl(WEB_CLIENT_URL)
                .build();
    }

    // Ejemplo de consumo de otro microservicio
    public Equipo obtenerEquipoRemoto(String equipoId) {
        return webClient.get()
                .uri("/{id}", equipoId)
                .retrieve()
                .bodyToMono(Equipo.class)
                .block();
    }

    // Obtener equipos del tipo solicitando
    public List<Equipo> consultarDisponibilidad(LocalDate fechaInicio, LocalDate fechaFin, String tipoEquipo) {

        // Obtener equipos del tipo solicitando
        List<Equipo> equipos = tipoEquipo != null ? equipoRepo.findByTipo(tipoEquipo) : equipoRepo.findByActivoTtrue();

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

    public boolean reservarEquipo(String equipoId, String pedidoId, LocalDate fechaInicio, LocalDate fechaFin) {
        // Verificar disponibilidad actual

        List<Disponibilidad> disponibilidades = disponibilidadRepo
                .findDisponibilidadPorRangoFechas(equipoId, fechaInicio, fechaFin);

        if (disponibilidades.isEmpty()) {
            return false;
        }
        Disponibilidad reserva = new Disponibilidad();
        reserva.setEquipoId(equipoId);
        reserva.setFechaInicio(fechaInicio);

        disponibilidadRepo.save(reserva);
        return true;
    }

    public void liberarEquipos(String pedidoId) {
        List<Disponibilidad> asignaciones = disponibilidadRepo.findByPedidoId(pedidoId);

        asignaciones.forEach(asignacion -> {

            disponibilidadRepo.save(asignacion);
        });

    }
}
