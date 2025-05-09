package com.MicroEntregas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.MicroEntregas.model.Entrega;
import com.MicroEntregas.repository.EntregaRepository;

@Service
public class EntregaService {
    private final EntregaRepository entregaRepository;

    public EntregaService(EntregaRepository entregaRepository){
        this.entregaRepository = entregaRepository;
    }
    public List<Entrega> obtenerTodasLasEntregas(){
        return entregaRepository.findAll();
    }
    public Optional<Entrega> obtenerEntregaPorId(long Id) {
        return entregaRepository.findById(Id);
    }
    public Entrega guardEntrega(Entrega entrega){
        return entregaRepository.save(entrega);
    }
    public void eliminarEntrega(long Id) {
        entregaRepository.deleteById(Id);
    }
    public Entrega actualizarEntregaCompleta(Long id, Entrega entregaActualizada) {
        return entregaRepository.findById(id).map(entregaExistente -> {
            entregaExistente.setEstado(entregaActualizada.getEstado());
            entregaExistente.setFechaEntrega(entregaActualizada.getFechaEntrega());
            entregaExistente.setTransportista(entregaActualizada.getTransportista());
            entregaExistente.setObservaciones(entregaActualizada.getObservaciones());
            return entregaRepository.save(entregaExistente);
        }).orElseThrow(() -> new RuntimeException("Entrega no encontrada con ID: " + id));
    }
    
    // Actualiza parcialmente una entrega existente
    // Se puede usar un Optional para evitar el uso de map
    //quiero hacer un cambio en el codigo y no usar map
    public Entrega actualizarEntregaParcial(long id, Entrega entregaActualizada) {
        Optional<Entrega> entregaExistente = entregaRepository.findById(id);
        if (entregaExistente.isPresent()) {
            Entrega entrega = entregaExistente.get();
            // Actualiza solo los campos no nulos
            if (entregaActualizada.getEstado() != null && !entregaActualizada.getEstado().isEmpty()) {
                entrega.setEstado(entregaActualizada.getEstado());
            }
            if (entregaActualizada.getFechaEntrega() != null) {
                entrega.setFechaEntrega(entregaActualizada.getFechaEntrega());
            }
            if (entregaActualizada.getTransportista() != null && !entregaActualizada.getTransportista().isEmpty()) {
                entrega.setTransportista(entregaActualizada.getTransportista());
            }
            if (entregaActualizada.getObservaciones() != null && !entregaActualizada.getObservaciones().isEmpty()) {
                entrega.setObservaciones(entregaActualizada.getObservaciones());
            }
            return entregaRepository.save(entrega);
        }
        throw new IllegalArgumentException("Entrega con id" + id + " no encontrada.");
    }
}