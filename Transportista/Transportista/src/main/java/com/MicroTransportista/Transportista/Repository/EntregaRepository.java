package com.MicroTransportista.Transportista.Repository;


import org.springframework.stereotype.Repository;

import com.MicroTransportista.Transportista.Model.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
    

}
