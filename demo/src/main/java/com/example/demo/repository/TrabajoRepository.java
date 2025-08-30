package com.example.demo.repository;

import com.example.demo.entity.Trabajo;
import com.example.demo.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, Long> {
    List<Trabajo> findByPersona(Persona persona);
}
