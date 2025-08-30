package com.example.demo.repository;

import com.example.demo.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByRut(String rut);
    Optional<Persona> findByCorreo(String correo);
    boolean existsByRut(String rut);
    boolean existsByCorreo(String correo);
}

package com.example.demo.repository;

import com.example.demo.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Persona findByCorreo(String correo);
}
