package org.microservicio__plazoleta.domain.usecase;

import org.microservicio__plazoleta.domain.api.IRestauranteServicePort;
import org.microservicio__plazoleta.domain.api.IUsuarioServicePort;
import org.microservicio__plazoleta.domain.model.Plato;
import org.microservicio__plazoleta.domain.model.Restaurante;
import org.microservicio__plazoleta.domain.spi.IRestaurantePersistencePort;
import org.microservicio__plazoleta.infrastructure.exception.BusinessException;

import java.util.List;

public class RestauranteUseCase implements IRestauranteServicePort {

    private final IRestaurantePersistencePort restaurantePersistence;
    private final IUsuarioServicePort usuarioService;

    public RestauranteUseCase(IRestaurantePersistencePort restaurantePersistence, IUsuarioServicePort usuarioService) {
        this.restaurantePersistence = restaurantePersistence;
        this.usuarioService = usuarioService;
    }

    @Override
    public void saveRestaurante(Restaurante restaurante) {
        validarRolUsuario(restaurante.getIdUsuario(), "ADMINISTRADOR");
        validarRolUsuario(restaurante.getIdRolPropietario(), "PROPIETARIO");
        restaurante.setIdUsuario(restaurante.getIdRolPropietario());
        validarDatosRestaurante(restaurante);
        restaurantePersistence.saveRestaurante(restaurante);
    }

    @Override
    public List<Restaurante> getAllRestaurantes(int page, int size) {
        return restaurantePersistence.findAllRestaurantsOrderedByName(page, size);
    }

    @Override
    public Long getRestauranteById(Long id) {
        return restaurantePersistence.getRestauranteById(id);
    }



    private void validarRolUsuario(Long idUsuario, String rolEsperado) {
        if (!rolEsperado.equalsIgnoreCase(usuarioService.obtenerRolUsuario(idUsuario))) {
            throw new BusinessException("El usuario no tiene rol de " + rolEsperado + ".");
        }
    }

    private void validarDatosRestaurante(Restaurante restaurante) {
        if (restaurante.getNit() == null || restaurante.getNit() <= 0) {
            throw new BusinessException("Documento de identidad debe ser un número positivo.");
        }
        if (restaurante.getTelefonoRestaurante() == null || !restaurante.getTelefonoRestaurante().matches("\\+57\\d{8,13}")) {
            throw new BusinessException("Teléfono inválido; máximo 13 dígitos y debe iniciar con '+57'.");
        }
        if (restaurante.getNombreRestaurante() == null || restaurante.getNombreRestaurante().trim().isEmpty()) {
            throw new BusinessException("El nombre del restaurante no puede estar vacío.");
        }
        if (restaurante.getNombreRestaurante().matches("\\d+")) {
            throw new BusinessException("El nombre del restaurante no puede ser solo números.");
        }
    }
}
