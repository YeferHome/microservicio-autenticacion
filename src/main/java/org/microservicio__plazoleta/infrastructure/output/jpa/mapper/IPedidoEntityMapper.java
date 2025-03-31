package org.microservicio__plazoleta.infrastructure.output.jpa.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.microservicio__plazoleta.domain.model.Pedido;
import org.microservicio__plazoleta.infrastructure.output.jpa.entity.PedidoEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPedidoEntityMapper {

    PedidoEntity toPedidoEntity(Pedido pedido);
    Pedido toPedido(PedidoEntity pedidoEntity);
}
