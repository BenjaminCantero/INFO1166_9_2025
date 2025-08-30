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

    /** Crea un trabajo y lo asocia a la persona correcta. */
    public Trabajo crearParaPersona(Long personaId, Trabajo t) {
        Persona persona = personaRepository.findById(personaId)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada: " + personaId));

        // Asegurar la relación en ambos lados
        persona.addTrabajo(t);
        // gracias al cascade de Persona podríamos guardar persona, pero es más simple guardar el trabajo:
        return trabajoRepository.save(t);
    }

    @Transactional(readOnly = true)
    public List<Trabajo> listarDePersona(Long personaId) {
        return trabajoRepository.findByPersonaId(personaId);
    }

    public void eliminar(Long trabajoId) {
        trabajoRepository.deleteById(trabajoId);
    }
}
