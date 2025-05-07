package com.example.Productos.Repository;

import org.springframework.stereotype.Repository;
import com.example.Productos.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
