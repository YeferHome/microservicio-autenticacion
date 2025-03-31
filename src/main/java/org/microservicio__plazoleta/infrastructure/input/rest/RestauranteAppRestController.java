package org.microservicio__plazoleta.infrastructure.input.rest;



import lombok.RequiredArgsConstructor;
import org.microservicio__plazoleta.application.dto.RestauranteAppRequestDto;
import org.microservicio__plazoleta.application.dto.RestauranteAppResponseDto;
import org.microservicio__plazoleta.application.handler.IRestauranteAppHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restauranteApp")
@RequiredArgsConstructor
public class RestauranteAppRestController {

    private final IRestauranteAppHandler restauranteAppHandler;

    @PostMapping("/save")
    public ResponseEntity<Void> saveRestauranteInRestauranteApp(@RequestBody RestauranteAppRequestDto restauranteAppRequestDto){
        restauranteAppHandler.saveRestauranteInRestauranteApp(restauranteAppRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<RestauranteAppResponseDto>> listRestaurantes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<RestauranteAppResponseDto> response = restauranteAppHandler.listRestaurantes(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/restaurante")
    public ResponseEntity<Long> getRestauranteById(@PathVariable Long id
    ) {
        Long response = restauranteAppHandler.getRestauranteById(id);
        return ResponseEntity.ok(response);
    }
}
