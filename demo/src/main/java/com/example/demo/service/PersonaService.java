package com.example.demo.service;

import com.example.demo.entity.Persona;
import com.example.demo.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public Persona guardar(Persona persona) {
        return personaRepository.save(persona);
    }

    public Persona buscarPorCorreo(String correo) {
        return personaRepository.findByCorreo(correo);
    }

    public Persona actualizar(Persona persona) {
        return personaRepository.save(persona);
    }
}
