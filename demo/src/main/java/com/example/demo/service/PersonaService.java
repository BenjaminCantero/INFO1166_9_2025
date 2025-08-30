package com.example.demo.service;

import com.example.demo.entity.Persona;
import com.example.demo.repository.PersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    // usado por /auth/register
    public Persona guardar(Persona persona) {
        return personaRepository.save(persona);
    }

    // usado por /api/me
    @Transactional(readOnly = true)
    public Persona buscarPorCorreo(String correo) {
        return personaRepository.findByCorreo(correo).orElse(null);
    }

    // usado por /api/me (PUT)
    public Persona actualizar(Persona persona) {
        // si viene con id nulo, save() inserta; si viene con id, actualiza
        return personaRepository.save(persona);
    }

    // utilidades adicionales (por si las necesitan)
    @Transactional(readOnly = true)
    public Persona obtenerPorId(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada: " + id));
    }

    @Transactional(readOnly = true)
    public List<Persona> listar() { return personaRepository.findAll(); }
}
