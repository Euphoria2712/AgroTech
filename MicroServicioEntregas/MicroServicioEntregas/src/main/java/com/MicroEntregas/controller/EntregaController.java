package com.MicroEntregas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.MicroEntregas.repository.EntregaRepository;
import com.MicroEntregas.service.EntregaService;
import com.MicroEntregas.model.Entrega;


@RestController
@RequestMapping("/api/v1/entregas")
public class EntregaController {

    private final EntregaRepository entregaRepository;
    @Autowired
    // Inyeccion de dependencias
    private  EntregaService entregaService;

    // Constructor para inyeccion de dependencias
    public EntregaController(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Entrega> obtenerEntregaPorId(@PathVariable long id) {
        Optional<Entrega> entrega = entregaService.obtenerEntregaPorId(id);
        return entrega.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public List<Entrega> obtenerTodasLasEntregas() {
        return entregaService.obtenerTodasLasEntregas();
    }

    
    
    @PostMapping
    public ResponseEntity<Entrega> crearEntrega(@RequestBody Entrega entrega) {
        Entrega nuevaEntrega = entregaService.guardEntrega(entrega);
        return ResponseEntity.ok(nuevaEntrega);
    }

    public Entrega guardEntrega(Entrega entrega) {
    return entregaRepository.save(entrega);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Entrega> actualizarEntrega(@PathVariable long id, @RequestBody Entrega entrega) {
    Optional<Entrega> entregaExistente = entregaService.obtenerEntregaPorId(id);
    if (entregaExistente.isPresent()) {
        Entrega entregaActualizada = entregaService.actualizarEntregaParcial(id, entrega);
        return ResponseEntity.ok(entregaActualizada);
    } else {
        return ResponseEntity.notFound().build();
    }
}
    @PutMapping("/{id}")
    public ResponseEntity<Entrega> actualizarEntregaCompleta(@PathVariable long id, @RequestBody Entrega entrega){
        Entrega entregaActualizada = entregaService.actualizarEntregaCompleta(id,entrega);
        return ResponseEntity.ok(entregaActualizada);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaResponseEntity(@PathVariable long id) {
        if (entregaService.obtenerEntregaPorId(id).isPresent()) {
            entregaService.eliminarEntrega(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}




