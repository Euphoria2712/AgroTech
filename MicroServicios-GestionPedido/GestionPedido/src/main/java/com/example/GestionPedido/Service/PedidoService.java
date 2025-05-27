package com.example.GestionPedido.Service;

import com.example.GestionPedido.Model.EstadoPedido;
import com.example.GestionPedido.Model.Pedido;
import com.example.GestionPedido.Model.ProductoDTO;
import com.example.GestionPedido.Model.UsuariosDTO;
import com.example.GestionPedido.Repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private WebClient webClient;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private StockClient stockClient;

    private static final String TOPIC_PEDIDO = "pedidos";

    // Consultar cliente (usa Usuarios como DTO, asegúrate de tener la clase
    // Usuarios en tu proyecto)
    public UsuariosDTO obtenerClientePorId(String clienteId) {
        return webClient.get()
                .uri("http://localhost:8080/api/v1/clientes/{id}", clienteId)
                .retrieve()
                .bodyToMono(UsuariosDTO.class)
                .block();
    }

    // Consultar stock (usa Producto como DTO)
    public ProductoDTO obtenerStockPorEquipoId(String equipoId) {
        return webClient.get()
                .uri("http://localhost:8081/api/v1/stock/{id}", equipoId)
                .retrieve()
                .bodyToMono(ProductoDTO.class)
                .block();
    }

    // Enviar mensaje a Kafka
    public void enviarMensajeKafka(String mensaje) {
        kafkaTemplate.send(TOPIC_PEDIDO, mensaje);
    }

    // Crear pedido y enviar mensaje a Kafka
    public Pedido crearPedido(Pedido pedido) {
        Pedido nuevoPedido = pedidoRepository.save(pedido);
        enviarMensajeKafka(nuevoPedido.toString());
        return nuevoPedido;
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

    public String crearPedido(Long clienteId, Long productoId, int cantidad) {
        String resultado = stockClient.ajustarStock(clienteId, productoId, cantidad).block();

        if (resultado.contains("Error")) {
            throw new RuntimeException("No se pudo ajustar el stock: " + resultado);
        }

        // Aquí guardarías el pedido si todo sale bien (opcional)

        return "Pedido creado y stock ajustado correctamente";
    }
}