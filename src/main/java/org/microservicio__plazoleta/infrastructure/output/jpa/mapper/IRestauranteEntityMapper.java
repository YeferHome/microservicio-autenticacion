package org.microservicio__plazoleta.infrastructure.output.jpa.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.microservicio__plazoleta.domain.model.Restaurante;
import org.microservicio__plazoleta.infrastructure.output.jpa.entity.RestauranteEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestauranteEntityMapper {

    RestauranteEntity toRestauranteEntity(Restaurante restaurante);
    Restaurante toRestaurante(RestauranteEntity restauranteEntity);
}
