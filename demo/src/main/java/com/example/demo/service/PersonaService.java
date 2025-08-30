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

    /** Guarda persona. La capa de security debería enviar el password ya hasheado. */
    public Persona crear(Persona p) {
        if (personaRepository.existsByRut(p.getRut())) {
            throw new IllegalArgumentException("RUT ya registrado");
        }
        if (personaRepository.existsByCorreo(p.getCorreo())) {
            throw new IllegalArgumentException("Correo ya registrado");
        }
        return personaRepository.save(p);
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

    public Persona actualizar(Long id, Persona cambios) {
        Persona p = obtenerPorId(id);
        p.setNombre(cambios.getNombre());
        p.setApellido(cambios.getApellido());
        p.setRut(cambios.getRut());
        p.setCorreo(cambios.getCorreo());
        p.setTelefono(cambios.getTelefono());
        if (cambios.getPassword() != null && !cambios.getPassword().isBlank()) {
            p.setPassword(cambios.getPassword());
        }
        return p; // gracias a @Transactional, se sincroniza al terminar
    }

    public void eliminar(Long id) {
        personaRepository.deleteById(id);
    }
}
