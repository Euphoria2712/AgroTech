package com.example.GestionPedido.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto_id", nullable = false)
    private Long idProducto;

    @Column(name = "transportista_id", nullable = false)
    private String transportistaId; // ID del transportista asignado al pedido

    @Column(name = "cliente_id", nullable = false)
    private String clienteId; // ID del cliente que hace el pedido

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "equipo_id", nullable = false)
    private String equipoId; // ID del equipo agrícola (maquinaria)

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pedido", nullable = false)
    private TipoPedido tipoPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoPedido estado = EstadoPedido.PENDIENTE;

    @Column(name = "fecha_pedido", nullable = false)
    private LocalDate fechaPedido = LocalDate.now();

    @Column(name = "fecha_entrega", nullable = false)
    private LocalDate fechaEntrega;

    private String condiciones; // Condiciones especiales (ej: "Pago en 30 días")

}
