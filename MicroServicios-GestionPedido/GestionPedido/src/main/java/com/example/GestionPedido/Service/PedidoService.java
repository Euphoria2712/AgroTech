package com.example.GestionPedido.Service;

import com.example.GestionPedido.Model.EstadoPedido;
import com.example.GestionPedido.Model.Pedido;
import com.example.GestionPedido.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido crearPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> actualizarEstado(Long id, EstadoPedido nuevoEstado) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setEstado(nuevoEstado);
                    if (nuevoEstado == EstadoPedido.ENTREGADO) {
                        pedido.setFechaEntrega(LocalDate.now());
                    }
                    return pedidoRepository.save(pedido);
                });
    }

    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> obtenerPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> obtenerPorCliente(String clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public List<Pedido> obtenerPorEstado(EstadoPedido estado) {
        return pedidoRepository.findByEstado(estado);
    }
}