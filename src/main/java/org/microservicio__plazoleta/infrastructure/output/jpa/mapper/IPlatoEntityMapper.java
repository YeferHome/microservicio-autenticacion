package org.microservicio__plazoleta.infrastructure.output.jpa.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.microservicio__plazoleta.domain.model.Plato;
import org.microservicio__plazoleta.infrastructure.output.jpa.entity.PlatoEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatoEntityMapper {
    PlatoEntity toPlatoEntity(Plato plato);

    Plato toPlato(PlatoEntity platoEntity);
}
