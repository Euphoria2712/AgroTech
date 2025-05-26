package com.example.GestionPedido.Repository;


import com.example.GestionPedido.Model.EstadoPedido;
import com.example.GestionPedido.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteId(String clienteId);
    List<Pedido> findByEstado(EstadoPedido estado);
    List<Pedido> findByTransportistaId(String transportistaId);
    List<Pedido> findByClienteIdAndEstado(String clienteId, EstadoPedido estado);
    List<Pedido> findByTransportistaIdAndEstado(String transportistaId, EstadoPedido estado);
}