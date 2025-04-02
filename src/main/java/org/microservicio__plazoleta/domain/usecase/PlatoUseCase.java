package org.microservicio__plazoleta.domain.usecase;

import org.microservicio__plazoleta.domain.api.IPlatoServicePort;
import org.microservicio__plazoleta.domain.api.IUsuarioServicePort;
import org.microservicio__plazoleta.domain.model.Plato;
import org.microservicio__plazoleta.domain.model.Restaurante;
import org.microservicio__plazoleta.domain.spi.IPlatoPersistencePort;
import org.microservicio__plazoleta.domain.spi.IRestaurantePersistencePort;
import org.microservicio__plazoleta.infrastructure.exception.BusinessException;

import java.util.List;

public class PlatoUseCase implements IPlatoServicePort {

    private final IPlatoPersistencePort platoPersistence;
    private final IRestaurantePersistencePort restaurantePersistence;
    private final IUsuarioServicePort usuarioService;

    public PlatoUseCase(IPlatoPersistencePort platoPersistence, IRestaurantePersistencePort restaurantePersistence, IUsuarioServicePort usuarioService) {
        this.platoPersistence = platoPersistence;
        this.restaurantePersistence = restaurantePersistence;
        this.usuarioService = usuarioService;
    }

    @Override
    public void savePlato(Plato plato) {
        verificarPropietario(plato.getIdUsuario());
        validarRestaurante(plato.getIdRestaurante(), plato.getIdUsuario());
        plato.setEstado(true);
        platoPersistence.savePlato(plato);
    }

    @Override
    public Plato updatePlato(Long idPlato, Plato platoModificado, Long idUsuario) {
        verificarPropietario(idUsuario);
        Plato platoUpdate = obtenerPlatoExistente(idPlato);
        validarRestaurante(platoUpdate.getIdRestaurante(), idUsuario);

        if (platoModificado.getPrecioPlato() != null && platoModificado.getPrecioPlato() > 0) {
            platoUpdate.setPrecioPlato(platoModificado.getPrecioPlato());
        } else {
            throw new BusinessException("El precio del plato debe ser un número positivo.");
        }

        if (platoModificado.getDescripcionPlato() != null && !platoModificado.getDescripcionPlato().trim().isEmpty()) {
            platoUpdate.setDescripcionPlato(platoModificado.getDescripcionPlato());
        } else {
            throw new BusinessException("La descripción del plato no puede estar vacía.");
        }
        return platoPersistence.savePlato(platoUpdate);
    }


    @Override
    public Plato updateEstadoPlato(Long idPlato, boolean nuevoEstado, Long idUsuario) {
        verificarPropietario(idUsuario);
        Plato plato = obtenerPlatoExistente(idPlato);
        validarRestaurante(plato.getIdRestaurante(), idUsuario);
        plato.setEstado(nuevoEstado);
        return platoPersistence.savePlato(plato);
    }

    @Override
    public List<Plato> getPlatosByRestaurante(Long restauranteId, String categoria, int page, int size) {
        return (categoria != null && !categoria.trim().isEmpty())
                ? platoPersistence.findByRestauranteAndCategoria(restauranteId, categoria, page, size)
                : platoPersistence.findByRestaurante(restauranteId, page, size);
    }

    private void verificarPropietario(Long idUsuario) {
        if (!"PROPIETARIO".equalsIgnoreCase(usuarioService.obtenerRolUsuario(idUsuario))) {
            throw new BusinessException("El usuario no tiene permisos para esta acción.");
        }
    }

    private void validarRestaurante(Long idRestaurante, Long idUsuario) {
        Restaurante restaurante = restaurantePersistence.findRestauranteById(idRestaurante);
        if (restaurante == null) {
            throw new BusinessException("El restaurante no existe.");
        }
        if (!restaurante.getIdUsuario().equals(idUsuario)) {
            throw new BusinessException("No eres propietario de este restaurante.");
        }
    }

    private Plato obtenerPlatoExistente(Long idPlato) {
        Plato plato = platoPersistence.findPlatoById(idPlato);
        if (plato == null) {
            throw new BusinessException("El plato no existe.");
        }
        return plato;
    }

    private void actualizarDatosPlato(Plato platoUpdate, Plato platoModificado) {
        platoUpdate.setDescripcionPlato(platoModificado.getDescripcionPlato());
        platoUpdate.setPrecioPlato(platoModificado.getPrecioPlato());
    }
}
