package com.example.GestionPedido.Controller;

import com.example.GestionPedido.Model.EstadoPedido;
import com.example.GestionPedido.Model.Pedido;
import com.example.GestionPedido.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoControlador {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        return pedidoService.crearPedido(pedido);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstado(
            @PathVariable Long id,
            @RequestParam EstadoPedido estado) {
        return pedidoService.actualizarEstado(id, estado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Pedido> obtenerTodos() {
        return pedidoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
        return pedidoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Pedido> obtenerPorCliente(@PathVariable String clienteId) {
        return pedidoService.obtenerPorCliente(clienteId);
    }

    @GetMapping("/estado/{estado}")
    public List<Pedido> obtenerPorEstado(@PathVariable EstadoPedido estado) {
        return pedidoService.obtenerPorEstado(estado);
    }
}