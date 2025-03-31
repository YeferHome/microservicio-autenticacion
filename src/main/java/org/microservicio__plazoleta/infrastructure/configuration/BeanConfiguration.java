package org.microservicio__plazoleta.infrastructure.configuration;



import lombok.RequiredArgsConstructor;
import org.microservicio__plazoleta.domain.api.IPedidoServicePort;
import org.microservicio__plazoleta.domain.api.IPlatoServicePort;
import org.microservicio__plazoleta.domain.api.IRestauranteServicePort;
import org.microservicio__plazoleta.domain.api.IUsuarioServicePort;
import org.microservicio__plazoleta.domain.spi.IPedidoPersistencePort;
import org.microservicio__plazoleta.domain.spi.IPlatoPersistencePort;
import org.microservicio__plazoleta.domain.spi.IRestaurantePersistencePort;
import org.microservicio__plazoleta.domain.usecase.PedidoUseCase;
import org.microservicio__plazoleta.domain.usecase.PlatoUseCase;
import org.microservicio__plazoleta.domain.usecase.RestauranteUseCase;
import org.microservicio__plazoleta.infrastructure.output.jpa.adapter.PedidoJpaAdapter;
import org.microservicio__plazoleta.infrastructure.output.jpa.adapter.PlatoJpaAdapter;
import org.microservicio__plazoleta.infrastructure.output.jpa.adapter.RestauranteJpaAdapter;
import org.microservicio__plazoleta.infrastructure.output.jpa.mapper.IPedidoEntityMapper;
import org.microservicio__plazoleta.infrastructure.output.jpa.mapper.IPlatoEntityMapper;
import org.microservicio__plazoleta.infrastructure.output.jpa.mapper.IRestauranteEntityMapper;
import org.microservicio__plazoleta.infrastructure.output.jpa.repository.IPedidoRepository;
import org.microservicio__plazoleta.infrastructure.output.jpa.repository.IPlatoRepository;
import org.microservicio__plazoleta.infrastructure.output.jpa.repository.IRestauranteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IPlatoRepository platoRepository;
    private final IPlatoEntityMapper platoEntityMapper;
    private final IRestauranteRepository restauranteRepository;
    private final IRestauranteEntityMapper restauranteEntityMapper;
    private final IUsuarioServicePort usuarioServicePort;
    private final IPedidoRepository pedidoRepository;
    private final IPedidoEntityMapper pedidoEntityMapper;

    @Bean
    public IPlatoPersistencePort platoPersistencePort(){
        return new PlatoJpaAdapter(platoRepository, platoEntityMapper);
    }

    @Bean
    public IPlatoServicePort platoServicePort(){
        return new PlatoUseCase(platoPersistencePort(),restaurantePersistencePort(), usuarioServicePort);
    }

    @Bean
    public IRestaurantePersistencePort restaurantePersistencePort(){
        return new RestauranteJpaAdapter(restauranteRepository, restauranteEntityMapper);
    }

    @Bean
    public IRestauranteServicePort restauranteServicePort(){
        return new RestauranteUseCase(restaurantePersistencePort(), usuarioServicePort);
    }

    @Bean
    public IPedidoPersistencePort pedidoPersistencePort(){
        return new PedidoJpaAdapter(pedidoRepository, pedidoEntityMapper);
    }

    @Bean
    public IPedidoServicePort pedidoServicePort(){
        return new PedidoUseCase(pedidoPersistencePort(), usuarioServicePort, restaurantePersistencePort());
    }

}


