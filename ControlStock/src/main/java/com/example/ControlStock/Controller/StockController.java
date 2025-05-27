package com.example.ControlStock.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ControlStock.Model.AjusteStockDTO;

import com.example.ControlStock.Model.ConsultaStockRequest;
import com.example.ControlStock.Model.NotificacionDTO;
import com.example.ControlStock.Model.Stock;
import com.example.ControlStock.Model.UsuarioDTO;
import com.example.ControlStock.Repository.StockRepositorio;
import com.example.ControlStock.Service.ClientClient;
import com.example.ControlStock.Service.NotificationClient;
import com.example.ControlStock.Service.ProductoClient;
import com.example.ControlStock.Service.StockService;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    @Autowired
    private StockService StockService;

    @Autowired
    private ProductoClient productoClient;

    @Autowired
    private StockRepositorio stockRepositorio;

    @Autowired
    private NotificationClient notificacionClient;

    @Autowired
    private ClientClient ClientClient;

@PostMapping("/ajustar")
public ResponseEntity<?> ajustarStock(@RequestBody AjusteStockDTO request) {
    try {
        // Validar cliente
        UsuarioDTO usuario = ClientClient.obtenerUsuarioPorId(request.getId()).block();
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(" Usuario no encontrado con ID: " + request.getId());
        }

        // Ajustar stock a través del servicio
        Stock stock = StockService.ajustarStock(request.getProductoId(), request.getCantidadSolicitada());

        // Verificar si después del ajuste el stock quedó bajo el mínimo
        if (stock.getCantidadActual() < stock.getCantidadMinima()) {
            NotificacionDTO alerta = new NotificacionDTO();
            alerta.setProductoId(stock.getProductoId());
            alerta.setMensaje(" Stock crítico tras ajuste. Producto ID: " + stock.getProductoId());
            alerta.setTipo("ALERTA");
            notificacionClient.enviarNotificacion(alerta).subscribe();
        }

        return ResponseEntity.ok(" Stock ajustado correctamente para el producto " + stock.getProductoId());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(" Error al ajustar el stock: " + e.getMessage());
    }
}

    @PostMapping("/consultar")
    public ResponseEntity<?> consultarStock(@RequestBody ConsultaStockRequest request) {
        try {
            Stock stock = StockService.consultarStock(request.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Stock no encontrado"));
            return ResponseEntity.ok("Cantidad en Stock: " + stock.getCantidadActual());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al consultar el stock: " + e.getMessage());
        }
    }

    @GetMapping("/producto/nombre")
    public Mono<ResponseEntity<String>> obtenerNombreProducto(@RequestParam Long id) {
        return productoClient.obtenerNombreProducto(id)
                .map(nombre -> ResponseEntity.ok("Nombre del producto: " + nombre))
                .onErrorResume(error -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al obtener nombre del producto: " + error.getMessage())));
    }

    @GetMapping("/verificar/{productoId}")
    public ResponseEntity<String> verificarStock(@PathVariable Long productoId) {
        Optional<Stock> stockOpt = stockRepositorio.findByProductoId(productoId);

        if (stockOpt.isPresent()) {
            Stock stock = stockOpt.get();

            if (stock.getCantidadActual() < stock.getCantidadMinima()) {
                NotificacionDTO alerta = new NotificacionDTO();
                alerta.setProductoId(productoId);
                alerta.setMensaje("¡Stock bajo para el producto con ID " + productoId + "!");
                alerta.setTipo("ALERTA");

                notificacionClient.enviarNotificacion(alerta)
                    .subscribe(
                        resp -> System.out.println("Notificación enviada: " + resp),
                        error -> System.err.println("Error al enviar notificación: " + error.getMessage())
                    );

                return ResponseEntity.ok("Stock bajo. Notificación enviada.");
            } else {
                return ResponseEntity.ok("Stock suficiente.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró stock para el producto con ID: " + productoId);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Stock> crearStockDesdeProducto(@RequestBody Stock stock) {
        Stock nuevo = stockRepositorio.save(stock);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/Validarcliente/{id}")
    public  ResponseEntity<String> validarCliente(@PathVariable Long id) {
        ClientClient.obtenerUsuarioPorId(id)
        .subscribe(usuario -> {
            System.out.println("Usuario obtenido: " + usuario.getNombre());
            
        }, error -> {
            System.err.println("Error al obtener el usuario: " + error.getMessage());

    });

        return ResponseEntity.ok("Cliente validado correctamente revisar consola");
    }
}
