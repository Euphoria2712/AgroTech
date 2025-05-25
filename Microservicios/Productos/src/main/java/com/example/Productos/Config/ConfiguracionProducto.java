package com.example.Productos.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Productos.Model.Producto;
import com.example.Productos.Repository.ProductoRepository;

@Configuration

public class ConfiguracionProducto {

    @Bean
    CommandLineRunner initDatabase(ProductoRepository productoRepository) {
        return args -> {
            // Verificar si ya existen datos en la base de datos
            if (productoRepository.count() == 0) {
                // Crear productos iniciales
                Producto producto1 = new Producto(null, "Producto A","Descripción del producto A", 10.0,1);
                Producto producto2 = new Producto(null, "Producto B", "Descripcion del producto B", 20.0, 2);

                // Guardar productos en la base de datos
                productoRepository.save(producto1);
                productoRepository.save(producto2);

                System.out.println("Datos iniciales guardados con éxito en la base de datos");
            } else {
                System.out.println("La base de datos ya contiene datos. No se insertaron nuevos registros.");
            }
        };
 

    }
}