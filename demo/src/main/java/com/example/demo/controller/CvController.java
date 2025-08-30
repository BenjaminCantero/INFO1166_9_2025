package com.example.demo.controller;

import com.example.demo.dto.DatosPersonalesDTO;
import com.example.demo.entity.Educacion;
import com.example.demo.entity.Experiencia;
import com.example.demo.entity.Persona;
import com.example.demo.service.CvService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cv")
public class CvController {

    private final CvService cvService;

    public CvController(CvService cvService) {
        this.cvService = cvService;
    }

    @PostMapping
    public Persona crearOActualizar(@RequestBody @Valid DatosPersonalesDTO dto) {
        return cvService.createOrUpdateFromDto(dto);
    }

    @GetMapping("/{id}")
    public Persona obtenerPorId(@PathVariable Long id) {
        return cvService.findPersonaById(id)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada: " + id));
    }

    @GetMapping
    public Object buscar(
            @RequestParam(required = false) String rut,
            @RequestParam(required = false) String correo) {

        if ((rut != null && !rut.isBlank()) || (correo != null && !correo.isBlank())) {
            return cvService.findPersonaByRutOrCorreo(rut, correo)
                    .orElseThrow(() -> new IllegalArgumentException("No existe persona con esos datos"));
        }
        return cvService.listAll(); // útil en desarrollo
    }

    @PostMapping("/{id}/experiencias")
    public Experiencia agregarExperiencia(@PathVariable Long id, @RequestBody Experiencia e) {
        return cvService.addExperiencia(id, e);
    }

    @PostMapping("/{id}/estudios")
    public Educacion agregarEducacion(@PathVariable Long id, @RequestBody Educacion ed) {
        return cvService.addEducacion(id, ed);
    }

    @DeleteMapping("/experiencias/{expId}")
    public void eliminarExperiencia(@PathVariable Long expId) {
        cvService.deleteExperiencia(expId);
    }

    @DeleteMapping("/estudios/{eduId}")
    public void eliminarEducacion(@PathVariable Long eduId) {
        cvService.deleteEducacion(eduId);
    }
}
