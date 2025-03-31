package org.microservicio__plazoleta.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlatoAppResponseDto {
    private String nombrePlato;
    private String descripcionPlato;
    private Long precioPlato;
    private String categoriaPlato;


}
