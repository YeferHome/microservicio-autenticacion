package org.microservicio__plazoleta.application.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.microservicio__plazoleta.application.dto.PedidoItemResponseDto;
import org.microservicio__plazoleta.application.dto.PedidoResponseDto;
import org.microservicio__plazoleta.domain.model.Pedido;
import org.microservicio__plazoleta.domain.model.PedidoItem;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPedidoAppResponseMapper {

PedidoResponseDto toPedidoResponseDto(Pedido pedido);

PedidoItemResponseDto toPedidoItemResponseDto(PedidoItem pedidoItem);
}
