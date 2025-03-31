package org.microservicio__plazoleta.application.handler;



import org.microservicio__plazoleta.application.dto.RestauranteAppRequestDto;
import org.microservicio__plazoleta.application.dto.RestauranteAppResponseDto;

import java.util.List;

public interface IRestauranteAppHandler {

    void saveRestauranteInRestauranteApp(RestauranteAppRequestDto restauranteAppRequestDto);
    List<RestauranteAppResponseDto> listRestaurantes(int page, int size);
    Long getRestauranteById(Long id);
}
