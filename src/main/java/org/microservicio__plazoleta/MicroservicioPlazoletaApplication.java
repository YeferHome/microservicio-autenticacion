package org.microservicio__plazoleta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroservicioPlazoletaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioPlazoletaApplication.class, args);
    }

}
