package com.MicroTransportista.Transportista.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.MicroTransportista.Transportista.Model.Transportista;
@Repository
public interface TransportistaRepository extends JpaRepository<Transportista, Long> {
    List<Transportista> findByDisponibleTrue();

    List<com.MicroTransportista.Transportista.Controller.Transportista> findAll();

    Transportista save(Transportista t);

}
