package org.microservicio__plazoleta.application.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.microservicio__plazoleta.application.dto.PlatoAppResponseDto;
import org.microservicio__plazoleta.application.dto.PlatoUpdateEstadoRequestDto;
import org.microservicio__plazoleta.application.dto.PlatoUpdateResponseDto;
import org.microservicio__plazoleta.domain.model.Plato;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatoAppResponseMapper {

    PlatoAppResponseDto toPlatoAppResponseDto(Plato plato);
    PlatoUpdateResponseDto toPlatoUpdateResponseDto(Plato plato);
    PlatoUpdateEstadoRequestDto toPlatoUpdateEstadoRequestDto(Plato plato);

}
