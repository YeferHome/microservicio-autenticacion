package org.microservicio__plazoleta.application.handler;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.microservicio__plazoleta.application.dto.*;
import org.microservicio__plazoleta.application.mapper.IPlatoAppRequestMapper;
import org.microservicio__plazoleta.application.mapper.IPlatoAppResponseMapper;
import org.microservicio__plazoleta.domain.api.IPlatoServicePort;
import org.microservicio__plazoleta.domain.model.Plato;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PlatoAppHandler implements IPlatoAppHandler {

    private final IPlatoServicePort platoServicePort;
    private final IPlatoAppRequestMapper iPlatoAppRequestMapper;
    private final IPlatoAppResponseMapper iPlatoAppResponseMapper;


    @Override
    public void savePlatoInPlatoApp(PlatoAppRequestDto platoAppRequestDto) {
        Plato plato = new Plato();
        plato.setNombrePlato(platoAppRequestDto.getNombrePlato());
        plato.setDescripcionPlato(platoAppRequestDto.getDescripcionPlato());
        plato.setPrecioPlato(platoAppRequestDto.getPrecioPlato());
        plato.setUrlPlato(platoAppRequestDto.getUrlPlato());
        plato.setCategoriaPlato(platoAppRequestDto.getCategoriaPlato());
        plato.setEstado(true);
        plato.setIdRestaurante(platoAppRequestDto.getIdRestaurante());
        plato.setIdUsuario(platoAppRequestDto.getIdUsuario());

        platoServicePort.savePlato(plato);
    }

    @Override
    public PlatoUpdateResponseDto updatePlatoInPlatoApp(Long platoId, PlatoUpdateRequestDto platoUpdateRequestDto, Long usuarioId) {
        Plato platoModificado = iPlatoAppRequestMapper.toPlato(platoUpdateRequestDto);
        Plato platoActualizado = platoServicePort.updatePlato(platoId, platoModificado, usuarioId);

        return iPlatoAppResponseMapper.toPlatoUpdateResponseDto(platoActualizado);
    }

    @Override
    public PlatoUpdateEstadoResponseDto updateEstadoPlatoInPlatoApp(Long platoId, Boolean nuevoEstado, Long usuarioId) {
        Plato platoActualizado = platoServicePort.updateEstadoPlato(platoId, nuevoEstado, usuarioId);


        return new PlatoUpdateEstadoResponseDto(
                platoActualizado.getEstado());
    }

    @Override
    public List<PlatoAppResponseDto> listPlatosMenu(Long idRestaurante, String categoria, int page, int size) {
        List<Plato> platos = platoServicePort.getPlatosByRestaurante(idRestaurante, categoria, page, size);
        return platos.stream()
                .map(iPlatoAppResponseMapper::toPlatoAppResponseDto)
                .collect(Collectors.toList());
    }

}
