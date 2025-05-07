package com.MicroEntregas.MicroServicioEntregas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MicroEntregas.MicroServicioEntregas.service.EntregaService;
import com.MicroEntregas.model.Entrega;


@RestController
@RequestMapping("/api/v1/entregas")
public class EntregaController {
    @Autowired
    // Inyeccion de dependencias
    private  EntregaService entregaService;
    
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
    public Entrega guardEntrega(@RequestBody Entrega entrega){
        return entregaService.guardEntrega(entrega);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaResponseEntity(@PathVariable long id){
        if(entregaService.obtenerEntregaPorId(id).isPresent()){
            entregaService.eliminarEntrega(id);
            return ResponseEntity.noContent().build();
        
        }
        return ResponseEntity.notFound().build();
    }
}
