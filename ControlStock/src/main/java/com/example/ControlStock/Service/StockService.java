package com.example.ControlStock.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ControlStock.Repository.StockRepositorio;
import com.example.ControlStock.Model.Stock;

@Service
public class StockService {

    @Autowired
    private StockRepositorio StockRepositorio;

public Stock ajustarStock(Long productoId, int cantidad) {
    Stock stock = StockRepositorio.findByProductoId(productoId)
        .orElse(Stock.builder()
                .productoId(productoId)
                .cantidadActual(0)
                .cantidadMinima(5)
                .build());

    stock.setCantidadActual(stock.getCantidadActual() - cantidad);
    return StockRepositorio.save(stock);
}

    public Optional<Stock> consultarStock(Long productoId){
        return StockRepositorio.findByProductoId(productoId);
    }

}
