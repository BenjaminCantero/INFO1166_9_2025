package com.example.demo.endPointsRest;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DatosPersonalesDTO;
import com.example.demo.service.DatosPersonalesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DatosPersonalesRestController {

    private final DatosPersonalesService datosPersonalesService;

    @PostMapping("/api/usuarios")
    public ResponseEntity<DatosPersonalesDTO> crearUsuario(@RequestBody DatosPersonalesDTO dto) {
        DatosPersonalesDTO guardado = datosPersonalesService.createComplete(dto);
        return ResponseEntity
                .created(URI.create("/api/usuarios/" + guardado.getIdDatosPersonales()))
                .body(guardado);
    }
}
