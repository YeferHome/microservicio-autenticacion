package org.microservicio__plazoleta.infrastructure.output.jpa.adapter;



import lombok.AllArgsConstructor;
import org.microservicio__plazoleta.domain.model.EstadoPedido;
import org.microservicio__plazoleta.domain.model.Pedido;
import org.microservicio__plazoleta.domain.spi.IPedidoPersistencePort;
import org.microservicio__plazoleta.infrastructure.output.jpa.entity.PedidoEntity;
import org.microservicio__plazoleta.infrastructure.output.jpa.entity.PedidoItemEntity;
import org.microservicio__plazoleta.infrastructure.output.jpa.mapper.IPedidoEntityMapper;
import org.microservicio__plazoleta.infrastructure.output.jpa.repository.IPedidoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@AllArgsConstructor
public class PedidoJpaAdapter implements IPedidoPersistencePort {

    private final IPedidoRepository pedidoRepository;
    private final IPedidoEntityMapper pedidoEntityMapper;

    @Override
    public Pedido savePedido(Pedido pedido) {
        PedidoEntity pedidoEntity = pedidoEntityMapper.toPedidoEntity(pedido);

        if (pedidoEntity.getItems() != null) {
            for (PedidoItemEntity itemEntity : pedidoEntity.getItems()) {
                itemEntity.setPedido(pedidoEntity);
            }
        }

        PedidoEntity savedPedidoEntity = pedidoRepository.save(pedidoEntity);
        return pedidoEntityMapper.toPedido(savedPedidoEntity);
    }

    @Override
    public boolean usuarioTienePedidoActivo(Long idUsuario) {

        return pedidoRepository.existsByIdClienteAndEstadoIn(
                idUsuario,
                List.of(EstadoPedido.PENDIENTE, EstadoPedido.EN_PREPARACION, EstadoPedido.LISTO));
    }

    @Override
    public Page<Pedido> findPedidosPorEstadoYRestaurante(EstadoPedido estado, Long restauranteId, PageRequest pageRequest) {

        Page<PedidoEntity> pedidoEntities = pedidoRepository.findByEstadoAndIdRestaurante(estado, restauranteId, pageRequest);

        return pedidoEntities.map(pedidoEntityMapper::toPedido);
    }
}
