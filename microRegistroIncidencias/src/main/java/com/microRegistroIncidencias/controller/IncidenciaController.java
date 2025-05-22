package com.microRegistroIncidencias.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microRegistroIncidencias.model.Incidencia;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/incidencias")


public class IncidenciaController {

    @PostMapping
    public Incidencia crear(@RequestBody Incidencia incidencia){
        //llamar al servicio
        return incidencia;

    }
    @GetMapping
    public List<Incidencia> Listar(){

        return List.of(); //retornara una lista bacia

    }
    @GetMapping("/{id}")
    public Incidencia ObtenerPorId(@PathVariable long id){
        return null;
        
    }
    @DeleteMapping("/{id}")
    public void eliminar(@RequestParam long id){
        
    }

    

}
    
    
    


