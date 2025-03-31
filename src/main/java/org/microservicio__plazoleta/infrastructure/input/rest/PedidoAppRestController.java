package org.microservicio__plazoleta.infrastructure.input.rest;


import lombok.RequiredArgsConstructor;
import org.microservicio__plazoleta.application.dto.PedidoRequestDto;
import org.microservicio__plazoleta.application.dto.PedidoResponseDto;
import org.microservicio__plazoleta.application.handler.IPedidoAppHandler;
import org.microservicio__plazoleta.domain.model.EstadoPedido;
import org.microservicio__plazoleta.domain.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidoApp")
@RequiredArgsConstructor
public class PedidoAppRestController {

    private final IPedidoAppHandler pedidoAppHandler;

    @PostMapping("/savePedido")
    public ResponseEntity<PedidoResponseDto> savePedido(@RequestBody PedidoRequestDto pedidoRequestDto) {
        PedidoResponseDto pedidoResponseDto = pedidoAppHandler.savePedido(pedidoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoResponseDto);
    }

    @GetMapping("/estado")
    @PreAuthorize("hasRole('ROLE_EMPLEADO')")
    public ResponseEntity<Page<Pedido>> getPedidosPorEstado(
            @RequestParam Long restauranteId,
            @RequestParam EstadoPedido estado,
            @RequestParam int page,
            @RequestParam int size) {

        Page<Pedido> pedidos = pedidoAppHandler.getPedidosPorEstado(restauranteId, estado, page, size);

        return ResponseEntity.ok(pedidos);
    }

}
