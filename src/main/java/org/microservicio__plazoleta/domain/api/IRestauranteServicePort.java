package org.microservicio__plazoleta.domain.api;



import org.microservicio__plazoleta.domain.model.Plato;
import org.microservicio__plazoleta.domain.model.Restaurante;

import java.util.List;

public interface IRestauranteServicePort {
    void saveRestaurante(Restaurante restaurante);
    List<Restaurante> getAllRestaurantes(int page, int size);
    Long getRestauranteById(Long id);

}
