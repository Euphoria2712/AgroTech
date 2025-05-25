package com.MicroTransportista.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MicroTransportista.model.Transportista;
import com.MicroTransportista.service.TransportistaService;

@RestController
@RequestMapping("/transportistas")
public class TransportistaController {
    
    @Autowired
    private TransportistaService service;

    @GetMapping
    public List<Transportista> listar(){
        return service.listar();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Transportista> obtenerTransportisEntity(@PathVariable long id){
        Transportista t = service.ObtenerPorId(id);
        if(t==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(t);
    }
    
    
    @PostMapping
    public Transportista crearTransportista(@RequestBody Transportista t){
        return service.crearTransportista(t);
    }
    @DeleteMapping("/{id}")
    public void eliminarTransportista(@PathVariable long id){
        service.eliminar(id);
    }
    @PostMapping("/{id}/estado")
    public Transportista cambiarEstado(@PathVariable long id, @RequestBody String nuevoEstado){
        return service.cambiarEstado(id, nuevoEstado);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Transportista> actualizarTransportista(@PathVariable long id, @RequestBody Transportista t){
        Transportista actualizado = service.actualizarTransportista(id, t);
        if(actualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

}
