package com.example.ControlStock.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.ControlStock.Model.Stock;
import com.example.ControlStock.Repository.StockRepositorio;

@Configuration
public class StockConfig {

    @Bean
    public CommandLineRunner initStock(StockRepositorio stockRepositorio) {
         return args -> {
            if (stockRepositorio.count()==0){
                stockRepositorio.save(new Stock(null, 1L, 20, 5));
                stockRepositorio.save(new Stock(null, 2L, 15, 5));
            }
        };
    }

}
