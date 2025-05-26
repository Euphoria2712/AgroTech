package com.MicroTransportista.repository;


import org.springframework.data.jpa.repository.JpaRepository;
//sexy
import com.MicroTransportista.model.Transportista;
public interface TransportistaRepository extends JpaRepository<Transportista, Long> {

}
