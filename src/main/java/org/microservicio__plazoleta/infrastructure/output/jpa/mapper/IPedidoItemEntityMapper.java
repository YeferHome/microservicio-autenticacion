package org.microservicio__plazoleta.infrastructure.output.jpa.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.microservicio__plazoleta.domain.model.PedidoItem;
import org.microservicio__plazoleta.infrastructure.output.jpa.entity.PedidoItemEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPedidoItemEntityMapper {

    PedidoItemEntity toPedidoItemEntity(PedidoItem pedidoItem);
    PedidoItem toPedidoItem(PedidoItemEntity pedidoItemEntity);
}
