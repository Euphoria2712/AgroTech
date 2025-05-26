package com.microRegistroIncidencias.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microRegistroIncidencias.model.Incidencia;
import com.microRegistroIncidencias.service.InsidenciaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/incidencias")


public class IncidenciaController {

    @Autowired
    private InsidenciaService incidenciaasService;

    @PostMapping
    public Incidencia crear(@RequestBody Incidencia incidencia){
        return incidenciaasService.crear(incidencia);
    }
    @GetMapping
    public List<Incidencia> listar(){
        return incidenciaasService.listar();
    }
    @GetMapping("/{id}")
    public Incidencia ObtenerPorId(@PathVariable long id) {
        return incidenciaasService.obtenerPorId(id);
    }
    @DeleteMapping("/{id}")
    public void eliminarPorId(@PathVariable long id) {
        incidenciaasService.eliminar(id);
    }
    
}
    
    
    


