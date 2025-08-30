package com.example.demo.service;

import com.example.demo.entity.Trabajo;
import com.example.demo.entity.Persona;
import com.example.demo.repository.TrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrabajoService {
    @Autowired
    private TrabajoRepository trabajoRepository;

    public Trabajo guardar(Trabajo trabajo) {
        return trabajoRepository.save(trabajo);
    }

    public List<Trabajo> listarPorPersona(Persona persona) {
        return trabajoRepository.findByPersona(persona);
    }
}
