package org.microservicio__plazoleta.infrastructure.output.jpa.repository;

import org.microservicio__plazoleta.infrastructure.output.jpa.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRestauranteRepository extends JpaRepository<RestauranteEntity, Long> {
}
