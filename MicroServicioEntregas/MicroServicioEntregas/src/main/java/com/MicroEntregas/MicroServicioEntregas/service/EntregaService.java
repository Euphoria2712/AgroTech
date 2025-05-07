package com.MicroEntregas.MicroServicioEntregas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.MicroEntregas.MicroServicioEntregas.repository.EntregaRepository;
import com.MicroEntregas.model.Entrega;

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
}
