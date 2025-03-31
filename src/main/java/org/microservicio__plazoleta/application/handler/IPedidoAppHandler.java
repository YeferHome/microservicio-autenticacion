package org.microservicio__plazoleta.application.handler;


import org.microservicio__plazoleta.application.dto.PedidoRequestDto;
import org.microservicio__plazoleta.application.dto.PedidoResponseDto;
import org.microservicio__plazoleta.domain.model.EstadoPedido;
import org.microservicio__plazoleta.domain.model.Pedido;
import org.springframework.data.domain.Page;

public interface IPedidoAppHandler {

    PedidoResponseDto savePedido(PedidoRequestDto requestDto);
    Page<Pedido> getPedidosPorEstado(Long restauranteId, EstadoPedido estado, int page, int size);


}
