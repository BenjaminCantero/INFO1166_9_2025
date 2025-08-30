package com.example.demo.security;

import com.example.demo.entity.Persona;
import com.example.demo.service.PersonaService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PersonaService personaService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public AuthController(PersonaService personaService, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.personaService = personaService;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    public Persona register(@RequestBody Persona persona) {
        persona.setPassword(passwordEncoder.encode(persona.getPassword()));
        return personaService.guardar(persona);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Persona persona) {
        Persona usuario = personaService.buscarPorCorreo(persona.getCorreo());
        if (usuario != null && passwordEncoder.matches(persona.getPassword(), usuario.getPassword())) {
            String token = tokenProvider.generarToken(usuario.getCorreo());
            return Map.of("token", token);
        }
        throw new RuntimeException("Correo o contraseña incorrecta");
    }
}
