package com.example.demo.service;

import com.example.demo.entity.Persona;
import com.example.demo.entity.Trabajo;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.repository.TrabajoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TrabajoService {

    private final TrabajoRepository trabajoRepository;
    private final PersonaRepository personaRepository;

    public TrabajoService(TrabajoRepository trabajoRepository, PersonaRepository personaRepository) {
        this.trabajoRepository = trabajoRepository;
        this.personaRepository = personaRepository;
    }

    // usado por /api/me/trabajos (controller ya setea persona)
    public Trabajo guardar(Trabajo trabajo) {
        return trabajoRepository.save(trabajo);
    }

    // usado por /api/me/trabajos (GET)
    @Transactional(readOnly = true)
    public List<Trabajo> listarPorPersona(Persona persona) {
        return trabajoRepository.findByPersona(persona);
    }

    // utilidades adicionales
    public Trabajo crearParaPersona(Long personaId, Trabajo t) {
        Persona p = personaRepository.findById(personaId)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada: " + personaId));
        p.addTrabajo(t);
        return trabajoRepository.save(t);
    }

    @Transactional(readOnly = true)
    public List<Trabajo> listarDePersona(Long personaId) {
        return trabajoRepository.findByPersonaId(personaId);
    }

    public void eliminar(Long trabajoId) { trabajoRepository.deleteById(trabajoId); }
}
