package org.microservicio__plazoleta.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestauranteAppResponseDto {

    private String nombreRestaurante;
    private String urlLogo;

}
