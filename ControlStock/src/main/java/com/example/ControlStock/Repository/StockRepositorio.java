package com.example.ControlStock.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ControlStock.Model.Stock;

@Repository
public interface StockRepositorio extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProductoId(Long productoId);
}
