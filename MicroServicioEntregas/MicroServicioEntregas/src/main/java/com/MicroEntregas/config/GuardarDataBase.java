package com.MicroEntregas.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.MicroEntregas.model.Entrega;
import com.MicroEntregas.repository.EntregaRepository;

@Configuration
public class GuardarDataBase {

    @Bean
    CommandLineRunner cargarDatosIniciales(EntregaRepository entregaRepository, UsuarioRepository usuarioRepository) {
        return args -> {
            //crear datos iniciales
            // el null lo creamos ya que el id es autogenerado por la base
            Entrega entrega1 = new Entrega("null","Transportista A","2023-10-01","2023-10-02","Entregada");
            Entrega entrega2 = new Entrega("null","transportista B","2024-10-02","2024-10-03","entregado");
            //guardar datos en la base de datos
            entregaRepository.save(entrega1);
            entregaRepository.save(entrega2);

            System.out.println("Datos iniciales guardados con exito en la base");
        };
    }


}
