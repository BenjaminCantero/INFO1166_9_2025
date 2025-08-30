package com.example.demo.controller;

import com.example.demo.entity.Persona;
import com.example.demo.entity.Trabajo;
import com.example.demo.service.PersonaService;
import com.example.demo.service.TrabajoService;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/me")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private TrabajoService trabajoService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    private String getCorreoDesdeToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        return tokenProvider.getUsernameFromJWT(token);
    }

    @GetMapping
    public Persona datosUsuario(HttpServletRequest request) {
        String correo = getCorreoDesdeToken(request);
        return personaService.buscarPorCorreo(correo);
    }

    @PutMapping
    public Persona actualizarDatos(@RequestBody Persona persona, HttpServletRequest request) {
        String correo = getCorreoDesdeToken(request);
        persona.setCorreo(correo);
        return personaService.actualizar(persona);
    }

    @PostMapping("/trabajos")
    public Trabajo agregarTrabajo(@RequestBody Trabajo trabajo, HttpServletRequest request) {
        String correo = getCorreoDesdeToken(request);
        Persona persona = personaService.buscarPorCorreo(correo);
        trabajo.setPersona(persona);
        return trabajoService.guardar(trabajo);
    }

    @GetMapping("/trabajos")
    public List<Trabajo> listarTrabajos(HttpServletRequest request) {
        String correo = getCorreoDesdeToken(request);
        Persona persona = personaService.buscarPorCorreo(correo);
        return trabajoService.listarPorPersona(persona);
    }
}
