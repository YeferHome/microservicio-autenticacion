package org.microservicio__plazoleta.domain.spi;



import org.microservicio__plazoleta.domain.model.Plato;
import org.microservicio__plazoleta.domain.model.Restaurante;

import java.util.List;

public interface IRestaurantePersistencePort {

     void saveRestaurante(Restaurante restaurante);
     Restaurante findRestauranteById(Long id);
     List<Plato> findPlatosByCategoria(Long restauranteId, String categoria, int page, int size);

     List<Restaurante> findAllRestaurantsOrderedByName(int page, int size);

     Long getRestauranteById(Long id);
     boolean elEmpleadoPerteneceAlRestaurante(Long restauranteId);

}
