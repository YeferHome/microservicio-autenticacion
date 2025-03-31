package org.microservicio__plazoleta.application.handler;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.microservicio__plazoleta.application.dto.RestauranteAppRequestDto;
import org.microservicio__plazoleta.application.dto.RestauranteAppResponseDto;
import org.microservicio__plazoleta.application.mapper.IRestauranteAppRequestMapper;
import org.microservicio__plazoleta.application.mapper.IRestauranteAppResponseMapper;
import org.microservicio__plazoleta.domain.api.IRestauranteServicePort;
import org.microservicio__plazoleta.domain.model.Restaurante;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class RestauranteAppHandler implements IRestauranteAppHandler {

    private final IRestauranteServicePort restauranteServicePort;
    private final IRestauranteAppResponseMapper restauranteAppResponseMapper;
    private final IRestauranteAppRequestMapper restauranteAppRequestMapper;

    @Override
    public void saveRestauranteInRestauranteApp(RestauranteAppRequestDto restauranteAppRequestDto) {
        restauranteServicePort.saveRestaurante(restauranteAppRequestMapper.toRestaurante(restauranteAppRequestDto));
    }

    @Override
    public List<RestauranteAppResponseDto> listRestaurantes(int page, int size) {
        List<Restaurante> restaurantes = restauranteServicePort.getAllRestaurantes(page, size);

        return restaurantes.stream()
                .map(restauranteAppResponseMapper::toRestauranteAppResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long getRestauranteById(Long id) {
        return restauranteServicePort.getRestauranteById(id);
    }

}
