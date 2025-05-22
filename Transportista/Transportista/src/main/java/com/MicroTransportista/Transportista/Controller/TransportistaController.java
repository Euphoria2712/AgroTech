package com.MicroTransportista.Transportista.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MicroTransportista.Transportista.Model.Transportista;
import com.MicroTransportista.Transportista.Repository.TransportistaRepository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/transportista")
public class TransportistaController {
    //que significa private final ?


    private final TransportistaRepository repo;

    public TransportistaController(TransportistaRepository repo){
        this.repo = repo;
    }
    @GetMapping
    public List <Transportista> listaTodos() {
        return repo.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Transportista> obtener(@PathVariable long id) {
        return repo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Transportista crear(@RequestBody Transportista t) {
        return repo.save(t);
        //TODO: process POST request
        
        return entity;
    }
    

}
