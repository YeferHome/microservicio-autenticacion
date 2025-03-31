package org.microservicio__plazoleta.application.handler;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.microservicio__plazoleta.application.dto.PedidoRequestDto;
import org.microservicio__plazoleta.application.dto.PedidoResponseDto;
import org.microservicio__plazoleta.application.mapper.IPedidoAppRequestMapper;
import org.microservicio__plazoleta.application.mapper.IPedidoAppResponseMapper;
import org.microservicio__plazoleta.domain.api.IPedidoServicePort;
import org.microservicio__plazoleta.domain.model.EstadoPedido;
import org.microservicio__plazoleta.domain.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidoHandler implements IPedidoAppHandler{

    private final IPedidoServicePort pedidoServicePort;
    private final IPedidoAppRequestMapper pedidoRequestMapper;
    private final IPedidoAppResponseMapper pedidoResponseMapper;

    @Override
    public PedidoResponseDto savePedido(PedidoRequestDto requestDto) {
        Pedido pedido = pedidoRequestMapper.toPedido(requestDto);

        Pedido pedidoCreado = pedidoServicePort.savePedido(pedido);

        return pedidoResponseMapper.toPedidoResponseDto(pedidoCreado);
    }


    @Override
    public Page<Pedido> getPedidosPorEstado(Long restauranteId, EstadoPedido estado, int page, int size) {
        return pedidoServicePort.getPedidosPorEstados(restauranteId, estado, page, size);
    }
}
