package com.example.demo.controller;

import com.example.demo.dto.DatosPersonalesDTO;
import com.example.demo.entity.*;
import com.example.demo.service.CvService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cv")
public class CvController {
    private final CvService service;

    public CvController(CvService service) {
        this.service = service;
    }

    @PostMapping
    public DatosPersonales crearOActualizar(@RequestBody DatosPersonalesDTO dto) {
        return service.createOrUpdate(dto);
    }

    @GetMapping
    public List<DatosPersonales> listar() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public DatosPersonales obtenerPorId(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada"));
    }

    @PostMapping("/{id}/laborales")
    public AntecedentesLaborales agregarLaboral(@PathVariable Long id,
            @RequestBody AntecedentesLaborales al) {
        return service.addLaboral(id, al);
    }

    @PostMapping("/{id}/estudios")
    public InformacionEstudios agregarEstudio(@PathVariable Long id,
            @RequestBody InformacionEstudios ie) {
        return service.addEstudio(id, ie);
    }
}
