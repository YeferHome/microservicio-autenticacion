package org.microservicio__plazoleta.domain.api;


import org.microservicio__plazoleta.domain.model.EstadoPedido;
import org.microservicio__plazoleta.domain.model.Pedido;
import org.springframework.data.domain.Page;

public interface IPedidoServicePort {

    Pedido savePedido(Pedido pedido);
    Page<Pedido> getPedidosPorEstados(Long restauranteId, EstadoPedido estado, int page, int size);
}
