package org.microservicio__plazoleta.infrastructure.output.jpa.adapter;

import lombok.RequiredArgsConstructor;
import org.microservicio__plazoleta.domain.model.Plato;
import org.microservicio__plazoleta.domain.spi.IPlatoPersistencePort;
import org.microservicio__plazoleta.infrastructure.exception.BusinessException;
import org.microservicio__plazoleta.infrastructure.output.jpa.entity.PlatoEntity;
import org.microservicio__plazoleta.infrastructure.output.jpa.mapper.IPlatoEntityMapper;
import org.microservicio__plazoleta.infrastructure.output.jpa.repository.IPlatoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PlatoJpaAdapter implements IPlatoPersistencePort {
    private final IPlatoRepository platoRepository;
    private final IPlatoEntityMapper platoEntityMapper;

    @Override
    public Plato savePlato(Plato plato) {
        platoRepository.save(platoEntityMapper.toPlatoEntity(plato));
        return plato;
    }

    @Override
    public Plato findPlatoById(Long idPlato) {
        return platoRepository.findById(idPlato)
                .map(platoEntityMapper::toPlato)
                .orElseThrow(() -> new BusinessException("El plato no existe"));
    }

    @Override
    public Plato updateEstadoPlato(Long idPlato, boolean nuevoEstado, Long idUsuario) {
        var platoEntity = platoRepository.findById(idPlato)
                .orElseThrow(() -> new BusinessException("El plato no existe"));


        platoEntity.setEstado(nuevoEstado);


        platoRepository.save(platoEntity);


        return platoEntityMapper.toPlato(platoEntity);
    }

    @Override
    public List<Plato> findByRestauranteAndCategoria(Long idRestaurante, String categoria, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PlatoEntity> pageResult = platoRepository.findAllByIdRestauranteAndCategoriaPlato(
                idRestaurante, categoria, pageable
        );

        return pageResult.getContent()
                .stream()
                .map(platoEntityMapper::toPlato)
                .collect(Collectors.toList());
    }


    @Override
    public List<Plato> findByRestaurante(Long idRestaurante, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PlatoEntity> pageResult = platoRepository.findAllByIdRestaurante(idRestaurante, pageable);

        return pageResult.getContent()
                .stream()
                .map(platoEntityMapper::toPlato)
                .collect(Collectors.toList());
    }
}
