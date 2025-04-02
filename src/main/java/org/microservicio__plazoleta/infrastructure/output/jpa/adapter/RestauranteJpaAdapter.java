package org.microservicio__plazoleta.infrastructure.output.jpa.adapter;

import lombok.RequiredArgsConstructor;
import org.microservicio__plazoleta.domain.model.Restaurante;
import org.microservicio__plazoleta.domain.spi.IRestaurantePersistencePort;
import org.microservicio__plazoleta.infrastructure.exception.BusinessException;
import org.microservicio__plazoleta.infrastructure.output.jpa.entity.RestauranteEntity;
import org.microservicio__plazoleta.infrastructure.output.jpa.mapper.IRestauranteEntityMapper;
import org.microservicio__plazoleta.infrastructure.output.jpa.repository.IRestauranteRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RestauranteJpaAdapter implements IRestaurantePersistencePort {

    private final IRestauranteRepository restauranteRepository;
    private final IRestauranteEntityMapper restauranteEntityMapper;

    @Override
    public void saveRestaurante(Restaurante restaurante) {
        restauranteRepository.save(restauranteEntityMapper.toRestauranteEntity(restaurante));
    }

    @Override
    public Restaurante findRestauranteById(Long id) {
        return restauranteRepository.findById(id)
                .map(restauranteEntityMapper::toRestaurante)
                .orElseThrow(() -> new BusinessException("El restaurante no existe"));
    }


    @Override
    public List<Restaurante> findAllRestaurantsOrderedByName(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("nombreRestaurante").ascending());
        Page<RestauranteEntity> pageResult = restauranteRepository.findAll(pageable);

        return pageResult.getContent()
                .stream()
                .map(restauranteEntityMapper::toRestaurante)
                .collect(Collectors.toList());
    }


    @Override
    public Long getRestauranteById(Long id) {
        return restauranteRepository.findById(id).get().getIdRestaurante();
    }

    @Override
    public boolean elEmpleadoPerteneceAlRestaurante(Long restauranteId) {
        return restauranteRepository.findById(restauranteId).isPresent();
    }


}
