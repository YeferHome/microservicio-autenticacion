package org.microservicio__plazoleta.infrastructure.input.rest.client;

import org.microservicio__plazoleta.infrastructure.configuration.feing.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-usuarios", url = "http://localhost:8080/usuarioApp", configuration = FeignClientConfig.class)
public interface UsuarioFeignClient {

    @GetMapping("/{id}/rol")
    String obtenerRol(@PathVariable("id") Long id);

    @GetMapping("/propietario/{idRestaurante}")
    Long obtenerIdPropietarioPorRestaurante(@PathVariable("idRestaurante") Long idRestaurante);
}

