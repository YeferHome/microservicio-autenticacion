package org.microservicio__plazoleta.domain.spi;


import org.microservicio__plazoleta.domain.model.Plato;

import java.util.List;

public interface   IPlatoPersistencePort {
    Plato savePlato(Plato plato);
    Plato findPlatoById(Long idPlato);
    Plato updateEstadoPlato(Long idPlato, boolean nuevoEstado, Long idUsuario);
    List<Plato> findByRestauranteAndCategoria(Long idRestaurante, String categoria, int page, int size);
    List<Plato> findByRestaurante(Long idRestaurante, int page, int size);

}
