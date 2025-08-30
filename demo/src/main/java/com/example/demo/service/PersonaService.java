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

    public Persona guardar(Persona persona) {
        return personaRepository.save(persona);
    }

    @Transactional(readOnly = true)
    public Persona buscarPorCorreo(String correo) {
        return personaRepository.findByCorreo(correo).orElse(null);
    }

    public Persona actualizar(Persona persona) {
        return personaRepository.save(persona);
    }

    @Transactional(readOnly = true)
    public Persona obtenerPorId(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada: " + id));
    }

    @Transactional(readOnly = true)
    public List<Persona> listar() {
        return personaRepository.findAll();
    }
}
