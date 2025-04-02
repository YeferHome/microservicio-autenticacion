package org.microservicio__plazoleta.domain.usecase;

import org.microservicio__plazoleta.domain.api.IPedidoServicePort;
import org.microservicio__plazoleta.domain.api.IUsuarioServicePort;
import org.microservicio__plazoleta.domain.model.EstadoPedido;
import org.microservicio__plazoleta.domain.model.Pedido;
import org.microservicio__plazoleta.domain.model.PedidoItem;
import org.microservicio__plazoleta.domain.spi.IPedidoPersistencePort;
import org.microservicio__plazoleta.domain.spi.IRestaurantePersistencePort;
import org.microservicio__plazoleta.infrastructure.exception.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class PedidoUseCase implements IPedidoServicePort {

    private final IPedidoPersistencePort pedidoPersistence;
    private final IUsuarioServicePort usuarioService;
    private final IRestaurantePersistencePort restaurantePersistence;

    public PedidoUseCase(IPedidoPersistencePort pedidoPersistence, IUsuarioServicePort usuarioService, IRestaurantePersistencePort restaurantePersistence) {
        this.pedidoPersistence = pedidoPersistence;
        this.usuarioService = usuarioService;
        this.restaurantePersistence = restaurantePersistence;
    }

    @Override
    public Pedido savePedido(Pedido pedido) {
        validarPedido(pedido);
        verificarPedidoActivo(pedido.getIdCliente());
        pedido.setEstado(EstadoPedido.PENDIENTE);
        return pedidoPersistence.savePedido(pedido);
    }

    @Override
    public Page<Pedido> getPedidosPorEstados(Long restauranteId, EstadoPedido estado, int page, int size) {
        verificarEmpleadoEnRestaurante(restauranteId);
        return pedidoPersistence.findPedidosPorEstadoYRestaurante(estado, restauranteId, PageRequest.of(page, size));
    }

    private void validarPedido(Pedido pedido) {
        if (pedido.getIdRestaurante() == null) {
            throw new BusinessException("El pedido debe especificar un restaurante.");
        }
        if (pedido.getItems() == null || pedido.getItems().isEmpty()) {
            throw new BusinessException("El pedido no tiene platos.");
        }
        Long idRestaurante = pedido.getIdRestaurante();
        for (PedidoItem item : pedido.getItems()) {
            if (!idRestaurante.equals(item.getIdRestaurante())) {
                throw new BusinessException("Todos los platos deben ser del mismo restaurante.");
            }
            if (item.getCantidad() <= 0) {
                throw new BusinessException("La cantidad de cada plato debe ser mayor a cero.");
            }
        }
    }

    private void verificarPedidoActivo(Long idCliente) {
        if (pedidoPersistence.usuarioTienePedidoActivo(idCliente)) {
            throw new BusinessException("El usuario ya tiene un pedido en proceso.");
        }
    }

    private void verificarEmpleadoEnRestaurante(Long idRestaurante) {
        if (!restaurantePersistence.elEmpleadoPerteneceAlRestaurante(idRestaurante)) {
            throw new BusinessException("El empleado no pertenece a este restaurante.");
        }
    }
}
