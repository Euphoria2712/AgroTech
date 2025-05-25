package com.example.ControlStock.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ControlStock.Model.AjusteStockRequest;
import com.example.ControlStock.Model.ConsultaStockRequest;
import com.example.ControlStock.Model.Stock;
import com.example.ControlStock.Service.StockService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    @Autowired
    private StockService StockService;

    @PostMapping("/ajustar")
    public ResponseEntity<?> ajustarStock(@RequestBody AjusteStockRequest request){
        try {
            Stock stock = StockService.ajustarStock(request.getProductoId(), request.getCantidad());
            return ResponseEntity.ok("Stock ajustado "+stock);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al ajustar el stock: " + e.getMessage());
        }

    }
  
    @PostMapping("/consultar")
    public ResponseEntity<?> consultarStock(@RequestBody ConsultaStockRequest request){
        try {
            Stock stock = StockService.consultarStock(request.getProductoId()).orElseThrow(() -> new RuntimeException("Stock no encontrado"));
            return ResponseEntity.ok("Cantidad en Stock: "+ stock.getCantidadActual());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al consultar el stock: " + e.getMessage());
        }
    }
    
    
    

}
