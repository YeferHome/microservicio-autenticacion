package org.microservicio__plazoleta.application.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.microservicio__plazoleta.application.dto.RestauranteAppResponseDto;
import org.microservicio__plazoleta.domain.model.Restaurante;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestauranteAppResponseMapper {
    RestauranteAppResponseDto toRestauranteAppResponseDto(Restaurante restaurante);
}
