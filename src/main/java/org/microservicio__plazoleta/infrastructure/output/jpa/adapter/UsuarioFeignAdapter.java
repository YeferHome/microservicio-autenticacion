package org.microservicio__plazoleta.infrastructure.output.jpa.adapter;


import lombok.AllArgsConstructor;
import org.microservicio__plazoleta.domain.api.IUsuarioServicePort;
import org.microservicio__plazoleta.infrastructure.input.rest.client.UsuarioFeignClient;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioFeignAdapter implements IUsuarioServicePort {

    private final UsuarioFeignClient usuarioFeignClient;

    @Override
    public String obtenerRolUsuario(Long id) {
        return usuarioFeignClient.obtenerRol(id);
    }
}
