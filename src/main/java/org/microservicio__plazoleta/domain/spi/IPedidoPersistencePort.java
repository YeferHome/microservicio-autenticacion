package org.microservicio__plazoleta.domain.spi;


import org.microservicio__plazoleta.domain.model.EstadoPedido;
import org.microservicio__plazoleta.domain.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IPedidoPersistencePort {

    Pedido savePedido(Pedido pedido);
    boolean usuarioTienePedidoActivo(Long idUsuario);
    Page<Pedido> findPedidosPorEstadoYRestaurante(EstadoPedido estado, Long restauranteId, PageRequest pageRequest);

}
