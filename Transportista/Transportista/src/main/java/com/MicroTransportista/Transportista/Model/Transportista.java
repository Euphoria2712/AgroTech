package com.MicroTransportista.Transportista.Model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Transportista {
    private long id;
    private String nombre;

    private boolean disponible;

    private String ubicacion;
    


}
