package com.microRegistroIncidencias.service;

import java.util.List;
import com.microRegistroIncidencias.model.Incidencia;
import com.microRegistroIncidencias.repository.IncidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsidenciaService {

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    public Incidencia crear(Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }

    public List<Incidencia> listar() {
        return incidenciaRepository.findAll();
    }

    public Incidencia obtenerPorId(long id) {
        return incidenciaRepository.findById(id).orElse(null);
    }

    public void eliminar(long id) {
        incidenciaRepository.deleteById(id);
    }

    public Incidencia actualizar(Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }

}
