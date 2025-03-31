package org.microservicio__plazoleta.infrastructure.input.rest;


import lombok.RequiredArgsConstructor;
import org.microservicio__plazoleta.application.dto.*;
import org.microservicio__plazoleta.application.handler.IPlatoAppHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platoApp")
@RequiredArgsConstructor
public class PlatoAppRestController {

private final IPlatoAppHandler platoAppHandler;

    @PostMapping("/save")
    public ResponseEntity<Void> savePlatoInPlatoApp(@RequestBody PlatoAppRequestDto platoAppRequestDto){
    platoAppHandler.savePlatoInPlatoApp(platoAppRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/{idPlato}/{idUsuario}")
    public ResponseEntity<PlatoUpdateResponseDto> updatePlatoInPlatoApp(
            @PathVariable("idPlato") Long idPlato,
            @PathVariable("idUsuario") Long idUsuario,
            @RequestBody PlatoUpdateRequestDto platoUpdateRequestDto) {
        PlatoUpdateResponseDto updatedPlato = platoAppHandler.updatePlatoInPlatoApp(idPlato, platoUpdateRequestDto, idUsuario);
        return ResponseEntity.ok(updatedPlato);
    }

    @PatchMapping("/{idPlato}/estado/{idUsuario}")
    public ResponseEntity<PlatoUpdateEstadoResponseDto> actualizarEstadoPlato(
            @PathVariable Long idPlato,
            @PathVariable Long idUsuario,
            @RequestBody PlatoUpdateEstadoRequestDto requestDto) {


        PlatoUpdateEstadoResponseDto updatedPlato =
                platoAppHandler.updateEstadoPlatoInPlatoApp(idPlato, requestDto.isEstado(), idUsuario);
        return ResponseEntity.ok(updatedPlato);
    }

    @GetMapping("/restaurantes/{idRestaurante}/menu")
    public ResponseEntity<List<PlatoAppResponseDto>> getMenuRestaurante(
            @PathVariable Long idRestaurante,
            @RequestParam(required = false) String categoria,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<PlatoAppResponseDto> response =
                platoAppHandler.listPlatosMenu(idRestaurante, categoria, page, size);
        return ResponseEntity.ok(response);
    }

}
