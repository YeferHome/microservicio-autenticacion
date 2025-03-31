package org.microservicio__plazoleta.application.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.microservicio__plazoleta.application.dto.PlatoAppRequestDto;
import org.microservicio__plazoleta.application.dto.PlatoUpdateEstadoRequestDto;
import org.microservicio__plazoleta.application.dto.PlatoUpdateRequestDto;
import org.microservicio__plazoleta.domain.model.Plato;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatoAppRequestMapper {

    Plato toPlato(PlatoAppRequestDto platoAppRequestDto);
    Plato toPlato(PlatoUpdateRequestDto platoUpdateRequestDto);
    String toEstadoString(PlatoUpdateEstadoRequestDto platoUpdateEstadoRequestDto);

}
