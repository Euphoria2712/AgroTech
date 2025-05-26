package com.example.GestionPedido.Model;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transportista_id", nullable = false)
    private String transportistaId;  // ID del transportista asignado al pedido

    @Column(name = "cliente_id", nullable = false)
    private String clienteId;  // ID del cliente que hace el pedido

    @Column(name = "equipo_id", nullable = false)
    private String equipoId;  // ID del equipo agrícola (maquinaria)

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pedido", nullable = false)
    private TipoPedido tipoPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoPedido estado = EstadoPedido.PENDIENTE;

    @Column(name = "fecha_pedido", nullable = false)
    private LocalDate fechaPedido = LocalDate.now();

    @Column(name = "fecha_entrega", nullable = false)
    @Temporal(TemporalType.DATE)
    // @temporal para la ora
    private LocalDate fechaEntrega;

    private String condiciones;  // Condiciones especiales (ej: "Pago en 30 días")

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public String getEquipoId() { return equipoId; }
    public void setEquipoId(String equipoId) { this.equipoId = equipoId; }
    public TipoPedido getTipoPedido() { return tipoPedido; }
    public void setTipoPedido(TipoPedido tipoPedido) { this.tipoPedido = tipoPedido; }
    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }
    public LocalDate getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(LocalDate fechaPedido) { this.fechaPedido = fechaPedido; }
    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }
    public String getCondiciones() { return condiciones; }
    public void setCondiciones(String condiciones) { this.condiciones = condiciones; }
}
