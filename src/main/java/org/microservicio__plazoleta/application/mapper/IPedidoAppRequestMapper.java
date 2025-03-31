package org.microservicio__plazoleta.application.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.microservicio__plazoleta.application.dto.PedidoItemRequestDto;
import org.microservicio__plazoleta.application.dto.PedidoRequestDto;
import org.microservicio__plazoleta.domain.model.Pedido;
import org.microservicio__plazoleta.domain.model.PedidoItem;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPedidoAppRequestMapper {

    Pedido toPedido(PedidoRequestDto pedidoRequestDto);

    PedidoItem toPedidoItem(PedidoItemRequestDto pedidoItemRequestDto);
}
