package com.MicroTransportista.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MicroTransportista.model.Transportista;
import com.MicroTransportista.repository.TransportistaRepository;

@Service
public class TransportistaServiceLogica implements TransportistaService{
    @Autowired
    private TransportistaRepository repo;

    @Override
    public Transportista crearTransportista(Transportista t){
        
        return repo.save(t);

    }
    @Override
    public List<Transportista> listar() {
        return repo.findAll();
    }
    @Override
    public void eliminar(long id) {
        repo.deleteById(id);
    }
    @Override
    public Transportista cambiarEstado(long id,String nuevoEstado){
        Transportista t = ObtenerPorId(id);
        t.setEstado(nuevoEstado);
        return repo.save(t);
    }
    @Override
    public Transportista ObtenerPorId(long id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Transportista no encontrado con id: " + id));

    }
   
    @Override
    public Transportista creTransportista(Transportista t) {
        return repo.save(t);
    }
    @Override
    public Transportista actualizarTransportista(long id, Transportista t) {
    Transportista existente = repo.findById(id).orElse(null);
    if (existente == null) {
        return null; // No crea uno nuevo, solo retorna null
    }
    existente.setNombre(t.getNombre());
    existente.setRut(t.getRut());
    existente.setTelefono(t.getTelefono());
    existente.setEstado(t.getEstado());
    return repo.save(existente);
}

}
