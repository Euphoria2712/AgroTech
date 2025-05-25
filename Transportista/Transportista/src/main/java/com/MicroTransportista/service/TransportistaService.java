package com.MicroTransportista.service;

import java.util.List;

import com.MicroTransportista.model.Transportista;

public interface TransportistaService {
    Transportista creTransportista(Transportista t);
    List<Transportista> listar();
    Transportista ObtenerPorId(long id);
    void eliminar(long id);
    Transportista cambiarEstado(long id,String nuevoEstado);
    Transportista crearTransportista(Transportista t);
    Transportista actualizarTransportista(long id, Transportista t);
    
    

}
